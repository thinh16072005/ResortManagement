package utils;

import java.util.regex.Pattern;
import java.util.Calendar;

public class EmployeeUtils {

    public static void validateEmployeeId(String employeeId) throws Exception {
        if (!Pattern.matches("NV-\\d{4}", employeeId)) {
            throw new Exception("Invalid employee ID. Must be in the format NV-YYYY.");
        }
    }

    public static void validateEmployeeName(String name) throws Exception {
        if (!Pattern.matches("([A-Z][a-z]*\\s*)+", name)) {
            throw new Exception("Invalid name. Each word must start with an uppercase letter.");
        }
    }

    public static void validateEmployeeDob(String dob) throws Exception {
        if (!Pattern.matches("\\d{2}/\\d{2}/\\d{4}", dob)) {
            throw new Exception("Invalid date of birth. Must be in the format dd/MM/yyyy.");
        }
        else {
            String[] parts = dob.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]) - 1; // Calendar months are 0-based
            int year = Integer.parseInt(parts[2]);

            Calendar dobCalendar = Calendar.getInstance();
            dobCalendar.set(day, month, year);

            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);

            if (today.get(Calendar.DAY_OF_YEAR) < dobCalendar.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }

            if (age < 18) {
                throw new Exception("Employee must be at least 18 years old.");
            }
        }
    }

    public static void validateEmployeeIdCard(String idCard) throws Exception {
        if (!Pattern.matches("\\d{9}|\\d{12}", idCard)) {
            throw new Exception("Invalid ID card. Must be between 9 and 12 digits.");
        }
    }

    public static void validateEmployeePhoneNumber(String phoneNumber) throws Exception {
        if (!Pattern.matches("\\d{10}", phoneNumber)) {
            throw new Exception("Invalid phone number. 10 digits max only!");
        }
    }

    public static void validateEmployeeEmail(String email) throws Exception {
        if (!Pattern.matches("\\w+@\\w+\\.\\w+", email)) {
            throw new Exception("Invalid email. Must be in the format <username>@<domain>.<extension>");
        }
    }

    public static void validateEmployeeSalary(double salary) throws Exception {
        if (salary <= 0) {
            throw new Exception("Invalid salary. Must be a positive number.");
        }
    }
}
