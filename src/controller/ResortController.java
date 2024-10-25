package controller;

import model.accomodation.House;
import model.accomodation.Room;
import model.accomodation.Villa;
import service.booking.BookingService;
import service.customer.CustomerService;
import service.employee.EmployeeService;
import service.facility.FacilityService;
import service.promotion.PromoService;
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
    public void execute(int ch) throws Exception {
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
    public void displayEmployeeMenu() throws Exception {
        EmployeeService empsrv = new EmployeeService();
        
        Menu employeeMenu = new Menu("\nEMPLOYEE OPTIONS", employeeOptions) {
            @Override
            public void execute(int ch) throws Exception{
                switch (ch) {
                    case 1 -> empsrv.display();
                    case 2 -> empsrv.add(null);
                    case 3 -> empsrv.update(null);
                }
            }
        };
        employeeMenu.run();
    }
    
    @Override
    public void displayCustomerMenu() throws Exception {
        CustomerService cussrv = new CustomerService();

        Menu customerMenu = new Menu("\nCUSTOMER OPTIONS", customerOptions) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 -> cussrv.display();
                    case 2 -> cussrv.add(null);
                    case 3 -> cussrv.update();
                }
            }
        };
        customerMenu.run();
    }

    @Override
    public void displayFacilityMenu() throws Exception {
        FacilityService facsrv = new FacilityService();

        Menu facilityMenu = new Menu("\nFACILITY OPTIONS", facilityOptions) {
            @Override
            public void execute(int ch) throws Exception {
                switch (ch) {
                    case 1 -> facsrv.display();
                    case 2 -> {
                        Menu addFacilityMenu = new Menu("\nADD FACILITY OPTIONS", addFacilityOptions) {
                            @Override
                            public void execute(int ch) {
                                switch (ch) {
                                    case 1 -> facsrv.add(new Villa());
                                    case 2 -> facsrv.add(new House());
                                    case 3 -> facsrv.add(new Room());
                                }
                            }
                        };
                        addFacilityMenu.run();
                    }
                    case 3 -> facsrv.displayFacilitiesForMaintenance();
                }
            }
        };
        facilityMenu.run();
    }

    @Override
    public void displayBookingMenu() throws Exception {
        BookingService bookingService = new BookingService();
        Menu bookingMenu = new Menu("\nBOOKING OPTIONS", bookingOptions) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 -> {
                        bookingService.add(null);
                    }
                    case 2 -> {
                        bookingService.display();
                    }
                    case 3 -> {
                         bookingService.addContract();
                    }
                    case 4 -> {
                         bookingService.displayContracts();
                    }
                    case 5 -> {
                         bookingService.editContract();
                    }
                }
            }
        };
        bookingMenu.run();
    }

    @Override
    public void displayPromotionMenu() throws Exception {
        PromoService promo = new PromoService();
        Menu promotionMenu = new Menu("\nPROMOTION OPTIONS", promotionOptions) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1 -> promo.displayCustomersByYear();
                    case 2 -> promo.distributeVouchers();
                }
            }
        };
        promotionMenu.run();
    }
    
    
    public static void main(String[] args) throws Exception {
        ResortController rs = new ResortController("----RESORT MANAGEMENT----", mainMenuOptions);
        rs.run();
    }
}