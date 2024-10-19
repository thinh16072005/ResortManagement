package repository.booking;

import model.reservation.Booking;
import model.reservation.Contract;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class BookingRepo implements IBookingRepo {
    protected TreeSet<Booking> bookingList = new TreeSet<>();

    @Override
    public TreeSet<Booking> readFile() {
        String line;
        try {
            BufferedReader input = new BufferedReader(new FileReader(path + bookingPath));

            while ((line = input.readLine()) != null) {
                String[] tokString = line.split(",");
                Date bookingDate = new SimpleDateFormat("dd/MM/yyyy").parse(tokString[1]);
                Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(tokString[2]);
                Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(tokString[3]);
                Booking booking = new Booking(tokString[0], bookingDate, startDate, endDate, tokString[4], tokString[5]);
                bookingList.add(booking);
            }
            return bookingList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeFile(TreeSet<Booking> bookings) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + bookingPath, true))) {
            for (Booking booking : bookings) {
                bw.write(booking.getBookingId() + "," + sdf.format(booking.getBookingDate()) + "," + sdf.format(booking.getStartDate()) + "," + sdf.format(booking.getEndDate()) + "," + booking.getCustomerId() + "," + booking.getServiceId());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void writeContractToCSV(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + contractPath, true))) {
            writer.write(contract.getContractId() + "," + contract.getBookingId() + "," + contract.getDeposit() + "," + contract.getTotalPrice());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing contract to CSV file: " + e.getMessage());
        }
    }
}
