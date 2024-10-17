package service.promotion;

import model.person.Customer;
import repository.promotion.PromoRepo;
import utils.Validation;

import java.util.ArrayList;
import java.util.Stack;

public class PromoService extends PromoRepo {

    public void displayCustomersByYear() {
        int year = Validation.checkInt("Enter year: ", "Invalid year. Please enter a valid year.");
        if (getCustomersByYear(year).isEmpty()) {
            System.err.println("No customers found for the year " + year);
        } else {
            getCustomersByYear(year).forEach(System.out::println);
        }
    }

    public void distributeVouchers() {
        int voucher10 = Validation.checkInt("Enter number of 10% vouchers: ", "Invalid number of vouchers. Please enter a valid number.");
        int voucher20 = Validation.checkInt("Enter number of 20% vouchers: ", "Invalid number of vouchers. Please enter a valid number.");
        int voucher50 = Validation.checkInt("Enter number of 50% vouchers: ", "Invalid number of vouchers. Please enter a valid number.");

        ArrayList<Customer> customers = getCustomersByCurrentMonth();
        Stack<Customer> customerStack = new Stack<>();
        customerStack.addAll(customers.subList(0, Math.min(customers.size(), voucher10 + voucher20 + voucher50)));

        System.out.println("Customers receiving 10% vouchers:");
        for (int i = 0; i < voucher10 && !customerStack.isEmpty(); i++) {
            System.out.println(customerStack.pop());
        }

        System.out.println("Customers receiving 20% vouchers:");
        for (int i = 0; i < voucher20 && !customerStack.isEmpty(); i++) {
            System.out.println(customerStack.pop());
        }

        System.out.println("Customers receiving 50% vouchers:");
        for (int i = 0; i < voucher50 && !customerStack.isEmpty(); i++) {
            System.out.println(customerStack.pop());
        }
    }
}