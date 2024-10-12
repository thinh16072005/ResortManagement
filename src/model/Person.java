package model;

import java.util.Date;
import java.util.regex.Pattern;

public abstract class Person {
    protected String name;
    protected Date dateOfBirth;
    protected boolean gender;
    protected String idCard;
    protected String phoneNumber;
    protected String email;

    public Person(String name, Date dateOfBirth, boolean gender, String idCard, String phoneNumber, String email) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        this.gender = gender;
        setIdCard(idCard);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!Pattern.matches("([A-Z][a-z]*\\s*)+", name)) {
            System.err.println("Invalid name. Each word must start with an uppercase letter.");
        }
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        if (!Pattern.matches("\\d{9,12}$", idCard)) {
            System.err.println("Invalid ID card. Must be between 9 and 12 digits.");
        }
        else {this.idCard = idCard;}
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!Pattern.matches("^\\d{1,10}$", phoneNumber)) {
            System.err.println("Invalid phone number. 10 digits max only!");
        }
        else {this.phoneNumber = phoneNumber;}
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!Pattern.matches("\\w+@\\w+\\.\\w+", email)) {
            System.err.println("Invalid email. Must be in the format <username>@<domain>.<extension>");
        }
        else {this.email = email;}
    }
}