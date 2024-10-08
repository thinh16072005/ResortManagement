/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.customer;

import model.person.Customer;
import repository.customer.CustomerRepo;
import utils.Validation;

/**
 * @author hungt
 */
public class CustomerService extends CustomerRepo implements ICustomerService {

    @Override
    public void save() {
        writeFile(cusList);
    }

    @Override
    public void add(Customer customer) {
        try {
            String id = Validation.getValue("Enter customer ID: ");
            String name = Validation.getValue("Enter customer name: ");
            String dob = Validation.getValue("Enter customer date of birth: ");
            String gender = Validation.getValue("Male or Female? (T/F): ");
            String idCard = Validation.getValue("Enter customer ID card: ");
            String phoneNumber = Validation.getValue("Enter customer phone number: ");
            String email = Validation.getValue("Enter customer email: ");
            String type = Validation.getValue("Enter customer type: ");
            String address = Validation.getValue("Enter customer address: ");
            customer = new Customer(id, name, Validation.convertStringToDate(dob), Validation.convertStringToBoolean(gender), idCard, phoneNumber, email, type, address);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
        cusList.add(customer);
        save();
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
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public void update(Customer e) {
        try {
            String id = Validation.getValue("Enter customer ID to update: ");
            Customer customer = find(id);

            String attribute = Validation.getValue("Enter attribute to update (name, dob, gender, idCard, phoneNumber, email, type, address): ");
            switch (attribute.toLowerCase()) {
                case "name":
                    customer.setName(Validation.getValue("Enter new name: "));
                    break;
                case "dob":
                    customer.setDateOfBirth(Validation.convertStringToDate(Validation.getValue("Enter new date of birth: ")));
                    break;
                case "gender":
                    customer.setGender(Validation.convertStringToBoolean(Validation.getValue("Male or Female? (T/F): ")));
                    break;
                case "idcard":
                    customer.setIdCard(Validation.getValue("Enter new ID card: "));
                    break;
                case "phonenumber":
                    customer.setPhoneNumber(Validation.getValue("Enter new phone number: "));
                    break;
                case "email":
                    customer.setEmail(Validation.getValue("Enter new email: "));
                    break;
                case "type":
                    customer.setCustomerType(Validation.getValue("Enter new level: "));
                    break;
                case "address":
                    customer.setAddress(Validation.getValue("Enter new position: "));
                    break;
                default:
                    System.out.println("Invalid attribute.");
                    return;
            }
            save();
        } catch (Exception ex) {
            System.out.println("Error updating customer: " + ex.getMessage());

        }
    }
}
