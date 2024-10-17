package service.employee;

import model.person.Employee;
import repository.employee.EmployeeRepo;
import utils.Validation;

import java.lang.reflect.Field;

public class EmployeeService extends EmployeeRepo implements IEmployeeService {

    @Override
    public void save() {
        try {
            writeFile(empList);
            System.out.println("Employee data saved.");
        } catch (Exception e) {
            System.err.println("Error saving employee data: " + e.getMessage());
        }
    }

    @Override
    public void add(Employee employee) {
        try {
            String id = Validation.getValue("Enter employee ID: ");
            String name = Validation.checkString("Enter employee name: ", "Name should contain letters only!", "^[a-zA-Z]+$");
            String dob = Validation.getValue("Enter employee date of birth: ");
            String gender = Validation.checkString("Male or Female? (Y/N): ", "Y/N only!", "^[Y, N]+$");
            String idCard = Validation.checkString("Enter employee ID card: ", "ID should contain numbers only!", "^[0-9]+$");
            String phoneNumber = Validation.checkString("Enter employee phone number: ", "Phone should contains numbers only!", "^[0-9]+$");
            String email = Validation.checkString("Enter employee email: ", "Invalid email!", "^[a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,2}$");
            String level = Validation.checkString("Enter employee level: ", "Level should contain letters only!", "^[a-zA-Z]+$");
            String position = Validation.checkString("Enter employee position: ", "Position should contain letters only!", "^[a-zA-Z]+$");
            double salary = Validation.checkDouble("Enter employee salary: ", "Invalid salary. Must be a number.");

            String confirm = Validation.continueConfirm("Do you want to add this employee? (Y/N): ", "Y/N only!");
            if (confirm.equalsIgnoreCase("N")) {
                return;
            }
            else {
                employee = new Employee(id, name, Validation.convertStringToDate(dob), Validation.convertStringToBoolean(gender), idCard, phoneNumber, email, level, position, salary);
                boolean exists = false;
                for (int i = 0; i < empList.size(); i++) {
                    if (empList.get(i).getEmployeeId().equals(id)) {
                        empList.set(i, employee);
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    empList.add(employee);
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
        readFile();
        empList.forEach(System.out::println);
    }

    @Override
    public Employee find(String id) throws Exception {
        readFile();
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
            String id = Validation.getValue("\nEnter employee ID to update: ");
            Employee employee = find(id);
            System.out.println("Employee found: ");
            System.out.println(employee);

            // Let the user choose which attribute to update
            // This is done by using reflection to get the fields of the superclass
            Class<?> customerFields = Class.forName("Employee");
            Field[] fields = customerFields.getSuperclass().getDeclaredFields();
            for (Field field : fields) {
                System.out.print("\t" + field.getName());
            }

            String attribute = Validation.getValue("\nEnter attribute to update: ");
            switch (attribute.toLowerCase()) {
                case "name" -> employee.setName(Validation.checkString("Enter employee name: ", "Name should contain letters only!", "^[A-Za-z\s]+$"));
                case "dob" -> employee.setDateOfBirth(Validation.convertStringToDate(Validation.getValue("Enter new date of birth: ")));
                case "gender" -> employee.setGender(Validation.convertStringToBoolean(Validation.getValue("Male or Female? (T/F): ")));
                case "idcard" -> employee.setIdCard(Validation.getValue("Enter new ID card: "));
                case "phonenumber" -> employee.setPhoneNumber(Validation.getValue("Enter new phone number: "));
                case "email" -> employee.setEmail(Validation.getValue("Enter new email: "));
                case "level" -> employee.setLevel(Validation.getValue("Enter new level: "));
                case "position" -> employee.setPosition(Validation.getValue("Enter new position: "));
                case "salary" -> employee.setSalary(Double.parseDouble(Validation.getValue("Enter new salary: ")));
                default -> {
                    System.err.println("Invalid attribute.");
                    return;
                }
            }
            save();
        } catch (Exception ex) {
            System.err.println("Error updating employee: " + ex.getMessage());
        }
    }
    
}
