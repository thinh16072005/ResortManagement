package model.person;

import java.util.Date;
import java.util.regex.Pattern;
import model.Person;


public class Customer extends Person {
    private String customerId;
    private String customerType;
    private String address;

    public Customer(String customerId, String name, Date dateOfBirth, boolean gender, String idCard, String phoneNumber, String email, String customerType, String address) {
        super(name, dateOfBirth, gender, idCard, phoneNumber, email);
        this.customerId = customerId;
        this.customerType = customerType;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        if (!Pattern.matches("CUS-\\d{4}", customerId)) {
            System.err.println("Invalid employee ID. Must be in the format CUS-YYYY.");
        }
        else {this.customerId = customerId;}
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        if (!customerType.equals("Member") && !customerType.equals("Silver") &&
            !customerType.equals("Gold") && !customerType.equals("Platinum") && !customerType.equals("Diamond"))
        {System.err.println("Invalid customer type. Must be one of: Member, Silver, Gold, Platinum, Diamond.");}
        else {this.customerType = customerType;}
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("""
                         | %-15s | %-20s |
                         +-----------------+----------------------+
                         | %-15s | %-20s |
                         +-----------------+----------------------+
                         | %-15s | %-20s |
                         | %-15s | %-20s |
                         | %-15s | %-20s |
                         | %-15s | %-20s |
                         | %-15s | %-20s |
                         | %-15s | %-20s |
                         | %-15s | %-20s |
                         | %-15s | %-20s |
                         +-----------------+----------------------+
                         """,
                "Field", "Value",
                "Customer ID", getCustomerId(),
                "Name", getName(),
                "Date of Birth", getDateOfBirth(),
                "Gender", getGender() ? "Male" : "Female",
                "ID Card", getIdCard(),
                "Phone Number", getPhoneNumber(),
                "Email", getEmail(),
                "Customer Type", getCustomerType(),
                "Address", getAddress()
        );
    }
}
