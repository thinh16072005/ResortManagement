/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author hungt
 */
public interface SubMenus {
    
    void displayEmployeeMenu();
    String[] employeeOptions = {
            "Display list employees",
            "Add new employee",
            "Edit employee",
            "Return to main menu",
        };
    
    void displayCustomerMenu();
    String[] customerOptions = {
            "Display list of customers",
            "Add new customers",
            "Edit customers",
            "Return main menu",
        };
    
    void displayFacilityMenu();
    String[] facilityOptions = {
            "Display list facility",
            "Add new facility",
            "Display list facility maintenance",
            "Return to main menu"
        };
    
    void displayBookingMenu();
    String[] bookingOptions = {
            "Add new booking",
            "Display list booking",
            "Create new contracts",
            "Display list of contracts",
            "Edit contracts",
            "Return to main menu",
        };
    
    void displayPromotionMenu();
    String[] promotionOptions = {
            "Display list of customers using the service",
            "Display list of customers getting the voucher",
            "Return to main menu"
        };
    
    
}
