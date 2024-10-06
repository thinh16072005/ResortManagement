
package service.employee;

import model.person.Employee;
import repository.employee.EmployeeRepo;
import utils.Validation;

public class EmployeeService extends EmployeeRepo implements IEmployeeService {

    @Override
    public void save() {
        writeFile(empList);
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
        while (true) {
            try {
                id = Validation.getValue("Enter employee ID: ");
                for (Employee emp : empList) {
                    if (emp.getEmployeeId().equalsIgnoreCase(id)) {
                        return emp;
                    }
                }
            } catch (Exception e) {
                System.out.println("Employee not found. Please try again.");
            }
        }
    }

    @Override
    public void update(Employee e) {
        find(id);
    }
    
}
