package service.customer;

import model.person.Customer;
import repository.customer.CustomerRepo;
import utils.Validation;

import java.lang.reflect.Field;

public class CustomerService extends CustomerRepo implements ICustomerService {
    private final CustomerRepo customerRepo = new CustomerRepo();

    @Override
    public void save() {
        try {
            writeFile(customerRepo.getCusList());
            System.out.println("Customer data saved.");
        } catch (Exception e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        }
    }

    @Override
    public void add(Customer customer) {
        try {
            String id = Validation.checkString("Enter customer ID: ", "Invalid customer ID. Must be in the format CUS-YYYY.", "^CUS-\\d{4}$");
            String name = Validation.checkString("Enter customer name: ", "Name should contain letters only!", "^[a-zA-Z ]+$");
            String dob = Validation.checkString("Enter customer date of birth: ", "Invalid date of birth. Must be in the format dd/MM/yyyy.", "^\\d{2}/\\d{2}/\\d{4}$");
            String gender = Validation.checkString("Male or Female? (Y/N): ", "Y/N only!", "^[Y, N]+$");
            String idCard = Validation.checkString("Enter customer ID card: ", "ID should contain numbers only!", "^[0-9]+$");
            String phoneNumber = Validation.checkString("Enter customer phone number: ", "Phone should contains numbers only!", "^[0-9]+$");
            String email = Validation.checkString("Enter customer email: ", "Invalid email!", "^[a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,2}$");
            String type = Validation.checkString("Enter customer type: ", "Invalid customer type. Must be one of: Member, Silver, Gold, Platinum, Diamond.", "^(Member|Silver|Gold|Platinum|Diamond)$");
            String address = Validation.getValue("Enter customer address: ");

            String confirm = Validation.continueConfirm("Do you want to add this customer? (Y/N): ", "Y/N only!");
            if (confirm.equalsIgnoreCase("N")) {
                System.out.println("Customer not added.");
                return;
            }
            else {
                customer = new Customer(id, name, Validation.convertStringToDate(dob), Validation.convertStringToBoolean(gender), idCard, phoneNumber, email, type, address);
                customerRepo.getCusList().add(customer);
                save();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void display() {
        readFile();
        System.out.println("-----------------------------------------------------------------------------  Customer List  ------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-20s | %-15s | %-10s | %-12s | %-15s | %-25s | %-15s | %-15s |\n",
                "ID", "Name", "Date of Birth", "Gender", "ID Card", "Phone Number", "Email", "Type", "Address");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Customer customer : getCusList()) {
            System.out.printf("%-10s | %-20s | %-15s | %-10s | %-12s | %-15s | %-25s | %-15s | %-15s |\n",
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getDateOfBirth(),
                    customer.getGender(),
                    customer.getIdCard(),
                    customer.getPhoneNumber(),
                    customer.getEmail(),
                    customer.getCustomerType(),
                    customer.getAddress()
            );
        }
    }

    @Override
    public Customer find(String entity) throws Exception {
        readFile();
        try {
            for (Customer cus : getCusList()) {
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
    public void update() {
        String id = Validation.getValue("Enter customer ID to update: ");
        try {
            Customer customer = find(id);
            System.out.println("Customer found: ");
            System.out.println(customer);

            Class<?> customerFields = Class.forName("model.person.Customer");

            Field[] fields = customerFields.getSuperclass().getDeclaredFields();
            for (Field field : fields) {
                System.out.print("\t" + field.getName());
            }
            
            String attribute = Validation.getValue("\nEnter attribute to update: ");
            switch (attribute.toLowerCase()) {
                case "name" -> customer.setName(Validation.getValue("Enter new name: "));
                case "dob" -> customer.setDateOfBirth(Validation.convertStringToDate(Validation.getValue("Enter new date of birth: ")));
                case "gender" -> customer.setGender(Validation.convertStringToBoolean(Validation.getValue("Male or Female? (T/F): ")));
                case "idcard" -> customer.setIdCard(Validation.getValue("Enter new ID card: "));
                case "phonenumber" -> customer.setPhoneNumber(Validation.getValue("Enter new phone number: "));
                case "email" -> customer.setEmail(Validation.getValue("Enter new email: "));
                case "type" -> customer.setCustomerType(Validation.getValue("Enter new type: "));
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
