package model.person;

import java.util.Date;
import model.Person;

public class Employee extends Person {
    private String employeeId;
    private String level;
    private String position;
    private double salary;

    public Employee(String employeeId, String name, Date dateOfBirth, boolean gender, String idCard, String phoneNumber, String email, String level, String position, double salary) {
        super(name, dateOfBirth, gender, idCard, phoneNumber, email);
        this.employeeId = employeeId;
        this.level = level;
        this.position = position;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    
    public boolean isGender() {
        return gender;
    }

    @Override
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
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
                "Gender", isGender() ? "Male" : "Female",
                "ID Card", getIdCard(),
                "Phone Number", getPhoneNumber(),
                "Email", getEmail(),
                "Level", getLevel(),
                "Position", getPosition(),
                "Salary", getSalary()
    );
}
}

