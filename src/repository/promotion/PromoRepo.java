package repository.promotion;

import model.person.Customer;
import model.reservation.Booking;
import repository.booking.BookingRepo;
import service.customer.CustomerService;

import java.time.LocalDate;
import java.util.ArrayList;

public class PromoRepo extends BookingRepo {
    CustomerService customerService = new CustomerService();
    ArrayList<Customer> customers = new ArrayList<>();

    protected ArrayList<Customer> getCustomersByYear(int year) {
        readFile();
        for (Booking booking : bookingList) {
            if (booking.getBookingDate().getYear() + 1900 == year) {
                try {
                    Customer customer = customerService.find(booking.getCustomerId());
                    if (customer != null) {
                        customers.add(customer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return customers;
    }

    protected ArrayList<Customer> getCustomersByCurrentMonth() {
        readFile();
        LocalDate now = LocalDate.now();
        for (Booking booking : bookingList) {
            if (booking.getBookingDate().getMonth() == now.getMonthValue()) {
                try {
                    Customer customer = customerService.find(booking.getCustomerId());
                    if (customer != null) {
                        customers.add(customer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return customers;
    }
}
