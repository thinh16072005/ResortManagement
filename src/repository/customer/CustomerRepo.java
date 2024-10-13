package repository.customer;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.person.Customer;

public class CustomerRepo implements ICustomerRepo {

    protected ArrayList<Customer> cusList = new ArrayList<>();

    @Override
    public void writeFile(ArrayList<Customer> customers) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path + customerPath, true))) {
            for (Customer customer : customers) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getDateOfBirth(),
                        customer.getGender(),
                        customer.getIdCard(),
                        customer.getPhoneNumber(),
                        customer.getEmail(),
                        customer.getCustomerType(),
                        customer.getAddress());
                output.write(line);
                output.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Customer> readFile() {
        String line;
        try {
            BufferedReader input = new BufferedReader(new FileReader(path + customerPath));

            while ((line = input.readLine()) != null) {
                String[] tokString = line.split(",");
                Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(tokString[2]);
                boolean gender = tokString[3].equals("Male") ? true : false;
                Customer customer = new Customer(tokString[0], tokString[1], dob, gender, tokString[4], tokString[5], tokString[6], tokString[7], tokString[8]);
                cusList.add(customer);
            }
            return cusList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
}
