package model.person;

import java.util.Date;
import java.util.regex.Pattern;
import model.Person;

public class Employee extends Person {
    private String employeeId;
    private String level;
    private String position;
    private double salary;

    public Employee(String employeeId, String name, Date dateOfBirth, boolean gender, String idCard, String phoneNumber, String email, String level, String position, double salary) {
        super(name, dateOfBirth, gender, idCard, phoneNumber, email);
        setEmployeeId(employeeId);
        setLevel(level);
        setPosition(position);
        setSalary(salary);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        if (!Pattern.matches("EMP-\\d{4}", employeeId)) {
            System.err.println("Invalid employee ID. Must be in the format EMP-YYYY.");
        }
        this.employeeId = employeeId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        if (!level.equals("Intermediate") && !level.equals("College") && 
            !level.equals("University") && !level.equals("Postgraduate")) {
            System.err.println("Invalid education level. Must be one of: Intermediate, College, University, Postgraduate.");
        }
        this.level = level;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (!position.equals("Receptionist") && !position.equals("Auditor") && !position.equals("Supervisor") &&
            !position.equals("Waitor") && !position.equals("F&B Manager") && !position.equals("HR Manager")){
            System.err.println("Invalid position. Must be one of: Receptionist, Auditor, Supervisor, Waitor, F&B Manager, HR Manager.");
        }
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            System.err.println("Invalid salary. Must be a positive number.");
        }
        this.salary = salary;
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
                        | %-15s | %-20s |
                        +-----------------+----------------------+
                        """,
                "Field", "Value",
                "Employee ID", getEmployeeId(),
                "Name", getName(),
                "Date of Birth", getDateOfBirth(),
                "Gender", getGender() ? "Male" : "Female",
                "ID Card", getIdCard(),
                "Phone Number", getPhoneNumber(),
                "Email", getEmail(),
                "Level", getLevel(),
                "Position", getPosition(),
                "Salary", getSalary()
        );
    }
}

