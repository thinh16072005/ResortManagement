package repository.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.person.Employee;

public class EmployeeRepo implements IEmployeeRepo {

    protected ArrayList<Employee> empList = new ArrayList<>();
    
    @Override
    public ArrayList<Employee> readFile() {
        String line;
        try {
            BufferedReader input = new BufferedReader(new FileReader(path + employeePath));

            while ((line = input.readLine()) != null) {
                String[] tokString = line.split(",");
                Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(tokString[2]);
                boolean gender = tokString[3].equals("Male") ? true : false;
                double salary = Double.parseDouble(tokString[9]);
                Employee employee = new Employee(tokString[0], tokString[1], dob, gender, tokString[4], tokString[5], tokString[6], tokString[7], tokString[8], salary);
                empList.add(employee);
            }
            return empList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeFile(ArrayList<Employee> employees) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path + employeePath, true))) {
            for (Employee employee : employees) {
                String line = employee.getEmployeeId() + "," +
                        employee.getName() + "," +
                        new SimpleDateFormat("dd/MM/yyyy").format(employee.getDateOfBirth()) + "," +
                        (employee.getGender() ? "Male" : "Female") + "," +
                        employee.getPhoneNumber() + "," +
                        employee.getEmail() + "," +
                        employee.getPosition() + "," +
                        employee.getSalary();
                output.write(line);
                output.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
