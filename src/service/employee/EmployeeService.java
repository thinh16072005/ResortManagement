
package service.employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import model.person.Employee;
import repository.employee.EmployeeRepo;
import utils.Validation;

public class EmployeeService extends EmployeeRepo implements IEmployeeService {

    @Override
    public void save() {
        try (var writer = new BufferedWriter(new FileWriter("employees.csv", true))) {
            for (model.person.Employee emp : empList) {
                writer.write(emp.toString());
                writer.newLine();
            }
            System.out.println("Employee data saved.");
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    @Override
    public void add(Employee employee) {
        try {
            String id = Validation.getValue("Enter employee ID: ");
            String name = Validation.getValue("Enter employee name: ");
            String dob = Validation.getValue("Enter employee date of birth: ");
            String gender = Validation.getValue("Male or Female? (T/F): ");
            String idCard = Validation.getValue("Enter employee ID card: ");
            String phoneNumber = Validation.getValue("Enter employee phone number: ");
            String email = Validation.getValue("Enter employee email: ");
            String level = Validation.getValue("Enter employee level: ");
            String position = Validation.getValue("Enter employee position: ");
            double salary = Double.parseDouble(Validation.getValue("Enter employee salary: "));
            employee = new Employee(id, name, Validation.convertStringToDate(dob), Validation.convertStringToBoolean(gender), idCard, phoneNumber, email, level, position, salary);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
        empList.add(employee);
        save();
    }

    @Override
    public void display() {
        readfile();
        for (Employee emp : empList) {
            System.out.println(emp);
        }
    }

    @Override
    public Employee find(String id) throws Exception {
        readfile();
        try {
            for (Employee emp : empList) {
                if (emp.getEmployeeId().equals(id)) {
                    return emp;
                }
            }
        } catch (Exception e) {
            throw new Exception("Employee not found.");
        }
        return null;
    }

    @Override
    public void update(Employee e) {
        try {
            String id = Validation.getValue("Enter employee ID to update: ");
            Employee employee = find(id);

            String attribute = Validation.getValue("Enter attribute to update (name, dob, gender, idCard, phoneNumber, email, level, position, salary): ");
            switch (attribute.toLowerCase()) {
            case "name":
                employee.setName(Validation.getValue("Enter new name: "));
                break;
            case "dob":
                employee.setDateOfBirth(Validation.convertStringToDate(Validation.getValue("Enter new date of birth: ")));
                break;
            case "gender":
                employee.setGender(Validation.convertStringToBoolean(Validation.getValue("Male or Female? (T/F): ")));
                break;
            case "idcard":
                employee.setIdCard(Validation.getValue("Enter new ID card: "));
                break;
            case "phonenumber":
                employee.setPhoneNumber(Validation.getValue("Enter new phone number: "));
                break;
            case "email":
                employee.setEmail(Validation.getValue("Enter new email: "));
                break;
            case "level":
                employee.setLevel(Validation.getValue("Enter new level: "));
                break;
            case "position":
                employee.setPosition(Validation.getValue("Enter new position: "));
                break;
            case "salary":
                employee.setSalary(Double.parseDouble(Validation.getValue("Enter new salary: ")));
                break;
            default:
                System.out.println("Invalid attribute.");
                return;
            }
            save();
        } catch (Exception ex) {
            System.out.println("Error updating employee: " + ex.getMessage());
        }
    }
    
}
