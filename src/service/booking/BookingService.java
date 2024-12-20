/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.booking;


import model.Facility;
import model.person.Customer;
import model.reservation.Booking;
import model.reservation.Contract;
import repository.booking.BookingRepo;
import repository.customer.CustomerRepo;
import service.customer.CustomerService;
import service.facility.FacilityService;
import utils.Validation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class BookingService extends BookingRepo implements IBookingService {
    CustomerService customerService = new CustomerService();
    FacilityService facilityService = new FacilityService();

    Queue<Booking> bookingQueue = new PriorityQueue<>(Comparator.comparing(Booking::getStartDate));

    @Override
    public Booking find(String entity) throws Exception {
        for (Booking booking : bookingList) {
            if (booking.getBookingId().equals(entity)) {
                return booking;
            }
        }
        throw new Exception("Booking not found.");
    }

    @Override
    public void display() {
        readFile();
        System.out.println("-----------------------------------------------------------------------------  Customer List  ------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-20s | %-15s | %-10s | %-12s | %-15s\n",
                "ID", "Booking date", "Start date", "End date", "Customer ID", "Service ID");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Booking booking : bookingList) {
            System.out.printf("%-10s | %-20s | %-15s | %-10s | %-12s | %-15s \n",
                    booking.getBookingId(),
                    booking.getBookingDate(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getCustomerId(),
                    booking.getServiceId()
            );
        }
    }

    @Override
    public void add(Booking bookings) {
        String bookingId = Validation.checkString("Enter booking ID: ", "Invalid booking ID. Must be in the format BKd.", "^BK\\d+$");

        System.out.println("List of Customers:");
        customerService.display();
        String customerId = Validation.checkString("Enter customer ID: ", "Invalid customer ID. Must be in the format CUS-YYYY.", "^CUS-\\d{4}$");

        facilityService.display();
        String serviceId = Validation.checkString("Enter service ID: ", "Invalid service ID. Must be in the format SV( VL | HO | RO )-YYYY.", "^SV(VL|HO|RO)-[0-9]{4}$");

        String bookingDate = Validation.checkString("Enter booking date: ", "Invalid booking date. Must be in the format dd/MM/yyyy.", "^\\d{2}/\\d{2}/\\d{4}$");
        String startDate = Validation.checkString("Enter start date: ", "Invalid start date. Must be in the format dd/MM/yyyy.", "^\\d{2}/\\d{2}/\\d{4}$");
        String endDate = Validation.checkString("Enter end date: ", "Invalid start date. Must be in the format dd/MM/yyyy.", "^\\d{2}/\\d{2}/\\d{4}$");

        String confirm = Validation.checkString("Do you want to add this booking? (Y/N): ", "Invalid input. Please enter Y or N.", "^[YyNn]$");
        if (confirm.equalsIgnoreCase("Y")) {
            Booking newBooking = new Booking(bookingId, Validation.convertStringToDate(bookingDate), Validation.convertStringToDate(startDate), Validation.convertStringToDate(endDate), customerId, serviceId);
            bookingList.add(newBooking);
            facilityService.incrementUsageCount(serviceId); // Add the booking to the facility usage count
            writeFile(bookingList);
            System.out.println("Booking added successfully.");
        } else {
            System.out.println("Booking not added.");
        }
    }

    public void addContract() {
        readFile();
        if (bookingList.isEmpty()) {
            System.out.println("No bookings available to process for contract creation.");
            return;
        }
        bookingQueue.addAll(bookingList);
        Booking booking;
        while (!bookingQueue.isEmpty()) {
            while ((booking = bookingQueue.poll()) != null) {
            if (booking.getServiceId().startsWith("SVVL") || booking.getServiceId().startsWith("SVHO")) {
                System.out.println(booking);
                String contractID = Validation.checkString("Enter contract ID: ", "Invalid contract ID. Must be in the format CTd.", "^CT\\d+$");
                Facility facility = facilityService.find(booking.getServiceId());
                if (facility == null) {
                    System.err.println("Facility not found for service ID: " + booking.getServiceId());
                    continue;
                }
                double deposit = Validation.checkDouble("Enter deposit amount: ", "Invalid deposit amount. Must be a positive number.");
                double totalAmount = facility.getRentingPrice() * facility.getMaxPeople() * facility.getAreaUsage() - deposit;
                Contract contract = new Contract(contractID, booking.getServiceId(), deposit, totalAmount);
                writeContractToCSV(contract);
                // System.out.println("Contract created successfully.");
            }
            }
        }
    }



    public void displayContracts() {
        bookingQueue.addAll(bookingList);
        System.out.println("Contracts:");
        while (!bookingQueue.isEmpty()) {
            Booking booking = bookingQueue.poll();
            if (booking.getServiceId().startsWith("SVVL") || booking.getServiceId().startsWith("SVHO")) {
                System.out.println(booking);
            }
        }
    }

    public void editContract() {
        bookingQueue.addAll(bookingList);
        System.out.println("Editing Contracts:");

    }

    @Override
    public void save() {
        try {
            writeFile(bookingList);
            System.out.println("Booking data saved.");
        } catch (Exception e) {
            System.out.println("Error saving booking data: " + e.getMessage());
        }
    }
}
