package utils;

import java.util.Scanner;

public class Validation {
    // Import scanner library:
    private static final Scanner sc = new Scanner(System.in);
    
    // Enable users enter value:
    public static String getValue(String msg){
        System.out.print(msg);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    // Check integer numbers:
    public static int checkInt(String msg, String errMsg) {
        int result = 0;
        while (true) {
            try {
                result = Integer.parseInt(getValue(msg));
                if (result <= 0) {
                    System.err.println("Enter a number larger than 0!");
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.err.println(errMsg);
            }
        }
    }
    
    // Check double numbers:
    public static double checkDouble(String msg, String errMsg) {
        double result = 0;
        while (true) {
            try {
                result = Double.parseDouble(getValue(msg));
                if (result <= 0) {
                    System.err.println("Enter a number larger than 0!");
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.err.println(errMsg);
            }
        }
    }
    
    // Check string input:
    public static String checkString(String msg, String errMsg, String regex) {
        while (true) {
            try {
                String result = getValue(msg);
                if (result.matches(regex)) return result;
                else System.err.println(errMsg);
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        }
    }
    
    // Check if user confirms:
    public static String continueConfirm(String msg, String errMsg) {
        while (true) {
            try {
                if (msg.equalsIgnoreCase("Y")) {
                    return "Y";
                } else if (msg.equalsIgnoreCase("N")) {
                    return "N";
                }
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        }
    }
    
}
