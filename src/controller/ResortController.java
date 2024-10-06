package controller;

import service.employee.EmployeeService;
import view.Menu;
import view.SubMenus;

public class ResortController extends Menu implements SubMenus {
    
    
    static String[] mainMenuOptions = {
        "Employee Management",
        "Customer Management",
        "Facility Management",
        "Booking Management",
        "Promotion Management",
        "Exit",
    };    


    public ResortController(String title, String[] mchon) {
        super(title, mchon);
    }
    
            
    @Override
    public void execute(int ch) {
        switch (ch) {
            case 1 -> displayEmployeeMenu();
            case 2 -> displayCustomerMenu();
            case 3 -> displayFacilityMenu();
            case 4 -> displayBookingMenu();
            case 5 -> displayPromotionMenu();
            case 6 -> System.exit(0);
            default -> throw new IllegalArgumentException("Option from 1-6 only!");
        }
        
    }

    @Override
    public void displayEmployeeMenu() {
        EmployeeService empsrv = new EmployeeService();
        
        Menu employeeMenu = new Menu("\nEMPLOYEE OPTIONS", employeeOptions) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 -> empsrv.display();
                    // case 2 -> empsrv.add(employee);
                    // case 3 -> empsrv.update(e);
                }
            }
        };
        employeeMenu.run();
    }
    
    @Override
    public void displayCustomerMenu() {
        Menu customerMenu = new Menu("\nCUSTOMER OPTIONS", customerOptions) {
            @Override
            public void execute(int ch) {
            }
        };
        customerMenu.run();
    }

    @Override
    public void displayFacilityMenu() {
        Menu facilityMenu = new Menu("\nFACILITY OPTIONS", facilityOptions) {
            @Override
            public void execute(int ch) {
                
            }
        };
        facilityMenu.run();
    }

    @Override
    public void displayBookingMenu() {
        Menu bookingMenu = new Menu("\nBOOKING OPTIONS", bookingOptions) {
            @Override
            public void execute(int ch) {
                
            }
        };
        bookingMenu.run();
    }

    @Override
    public void displayPromotionMenu() {
        Menu promotionMenu = new Menu("\nPROMOTION OPTIONS", promotionOptions) {
            @Override
            public void execute(int ch) {
                
            }
        };
        promotionMenu.run();
    }
    
    
    public static void main(String[] args) {
        ResortController rs = new ResortController("----RESORT MANAGEMENT----", mainMenuOptions);
        rs.run();
    }
}