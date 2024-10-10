package service.customer;

import model.person.Customer;
import repository.customer.CustomerRepo;
import utils.Validation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomerService extends CustomerRepo implements ICustomerService {

    @Override
    public void save() {
        try (var writer = new BufferedWriter(new FileWriter("customer.csv", true))) {
            for (Customer cus : cusList) {
                writer.write(cus.toString());
                writer.newLine();
            }
            System.out.println("Employee data saved.");
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }

    }

    @Override
    public void add(Customer customer) {
        try {
            String id = Validation.checkString("Enter customer ID: ", "Invalid customer ID. Must be in the format CUS-YYYY.", "^CUS-\\d{4}$");
            String name = Validation.checkString("Enter customer name: ", "Name should contain letters only!", "^[a-zA-Z]+$");
            String dob = Validation.checkString("Enter customer date of birth: ", "Invalid date of birth. Must be in the format dd/MM/yyyy.", "^\\d{2}/\\d{2}/\\d{4}$");
            String gender = Validation.checkString("Male or Female? (Y/N): ", "Y/N only!", "^[Y, N]+$");
            String idCard = Validation.checkString("Enter customer ID card: ", "ID should contain numbers only!", "^[0-9]+$");
            String phoneNumber = Validation.checkString("Enter customer phone number: ", "Phone should contains numbers only!", "^[0-9]+$");
            String email = Validation.checkString("Enter customer email: ", "Invalid email!", "^[a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,2}$");
            String type = Validation.checkString("Enter customer type: ", "Invalid customer type. Must be one of: Member, Silver, Gold, Platinum, Diamond.", "^(Member|Silver|Gold|Platinum|Diamond)$");
            String address = Validation.getValue("Enter customer address: ");

            String confirm = Validation.continueConfirm("Do you want to add this customer? (Y/N): ", "Y/N only!");
            if (confirm.equalsIgnoreCase("N")) {
                return;
            }
            else {
                customer = new Customer(id, name, Validation.convertStringToDate(dob), Validation.convertStringToBoolean(gender), idCard, phoneNumber, email, type, address);
                boolean exists = false;
                for (int i = 0; i < cusList.size(); i++) {
                    if (cusList.get(i).getCustomerId().equals(id)) {
                        cusList.set(i, customer);
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    cusList.add(customer);
                }
                save();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void display() {
        readfile();
        for (Customer cus : cusList) {
            System.out.println(cus);
        }
    }

    @Override
    public Customer find(String entity) throws Exception {
        readfile();
        try {
            for (Customer cus : cusList) {
                if (cus.getCustomerId().equals(entity)) {
                    return cus;
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public void update(Customer e) {
        try {
            String id = Validation.getValue("Enter customer ID to update: ");
            Customer customer = find(id);
            System.out.println("Customer found: ");
            System.out.println(customer);

            String attribute = Validation.getValue("Enter attribute to update " +
                    "(name, dob, gender, idcard, phonenumber, email, type, address): ");
            switch (attribute.toLowerCase()) {
                case "name" -> customer.setName(Validation.getValue("Enter new name: "));
                case "dob" -> customer.setDateOfBirth(Validation.convertStringToDate(Validation.getValue("Enter new date of birth: ")));
                case "gender" -> customer.setGender(Validation.convertStringToBoolean(Validation.getValue("Male or Female? (T/F): ")));
                case "idcard" -> customer.setIdCard(Validation.getValue("Enter new ID card: "));
                case "phonenumber" -> customer.setPhoneNumber(Validation.getValue("Enter new phone number: "));
                case "email" -> customer.setEmail(Validation.getValue("Enter new email: "));
                case "type" -> customer.setCustomerType(Validation.getValue("Enter new level: "));
                case "address" -> customer.setAddress(Validation.getValue("Enter new position: "));
                default -> {
                    System.out.println("Invalid attribute.");
                    return;
                }
            }
            save();
        } catch (Exception ex) {
            System.out.println("Error updating customer: " + ex.getMessage());

        }
    }
}
