package service.facility;

import model.Facility;
import model.accomodation.House;
import model.accomodation.Room;
import model.accomodation.Villa;
import repository.facility.FacilityRepo;
import utils.Validation;

import java.util.Map;

public class FacilityService extends FacilityRepo implements IFacilityService {
    @Override
    public void save() {
        try {
            writeFile(facilityUsageMap);
            System.out.println("Facility data saved.");
        } catch (Exception e) {
            System.err.println("Error saving facility data: " + e.getMessage());
        }
    }

    @Override
    public Facility find(String id) {
        for (Facility facility : facilityUsageMap.keySet()) {
            if (facility.getServiceId().equals(id)) {
                return facility;
            }
        }
        return null;
    }

    @Override
    public void display() {
        readFile();
        if (facilityUsageMap.isEmpty()) {
            System.err.println("No facilities found.");
        } else {
            System.out.printf("%-10s %-15s %-10s %-15s %-10s %-15s %-15s %-10s\n",
                    "ID", "Name", "Area", "Price", "Max People", "Type", "Room Standards", "Floors");
            System.out.println("-----------------------------------------------------------------------------------");
            for (Map.Entry<Facility, Integer> entry : facilityUsageMap.entrySet()) {
                System.out.println(entry.getKey() + " - Usage: " + entry.getValue());
            }
        }
    }

    public void incrementUsageCount(String facilityId) {
        for (Facility facility : facilityUsageMap.keySet()) {
            if (facility.getServiceId().equals(facilityId)) {
                facilityUsageMap.put(facility, facilityUsageMap.get(facility) + 1);
                writeFile(facilityUsageMap);
                break;
            } else {
                System.err.println("Facility not found.");
                return;
            }
        }
    }

    public void displayFacilitiesForMaintenance() {
        readFile();
        if (facilityUsageMap.isEmpty()) {
            System.err.println("No facilities found.");
        } else {
            System.err.println("Facilities that need maintenance: ");
            System.out.printf("%-10s %-15s %-10s %-15s %-10s %-15s %-15s %-10s\n",
                    "ID", "Name", "Area", "Price", "Max People", "Type", "Room Standards", "Floors");
            System.out.println("-----------------------------------------------------------------------------------");
            for (Map.Entry<Facility, Integer> entry : facilityUsageMap.entrySet()) {
                if (entry.getValue() >= 5) { // Assuming maintenance is needed after 5 uses
                    System.out.println(entry.getKey() + " - Usage: " + entry.getValue());
                }
            }
        }
    }

    @Override
    public void add(Facility facility) {
        String serviceId = Validation.checkString("Enter service ID: ", "Service ID must be in format SVXX-YYYY with X is V, H, R and Y is 4 digits", "^SV(VL|HO|RO)-[0-9]{4}$");
        String serviceName = Validation.checkString("Enter service name: ", "Service name must start with an uppercase letter", "^[A-Z].*");
        double areaUsage = Validation.checkDouble("Enter area usage: ", "Area usage must be a positive number");
        double rentingPrice = Validation.checkDouble("Enter renting price: ", "Renting price must be a positive number");
        int maxPeople = Validation.checkInt("Enter max people: ", "Max people must be a positive number");
        String rentingType = Validation.checkString("Enter renting type: ", "Renting type must be either day, week, or month", "^(day|week|month)$");

        facility.setServiceId(serviceId);
        facility.setServiceName(serviceName);
        facility.setAreaUsage(areaUsage);
        facility.setRentingPrice(rentingPrice);
        facility.setMaxPeople(maxPeople);
        facility.setRentingType(rentingType);

        switch (facility) {
            case Villa villa -> {
                String roomStandards = Validation.checkString("Enter room standards: ", "Room standards must be a string", "^[a-zA-Z]+$");
                double poolArea = Validation.checkDouble("Enter pool area: ", "Pool area must be a positive number");
                int floorQuantity = Validation.checkInt("Enter floor quantity: ", "Floor quantity must be a positive number");
                villa.setRoomStandards(roomStandards);
                villa.setPoolArea(poolArea);
                villa.setFloorQuantity(floorQuantity);

            }
            case House house -> {
                String roomStandards = Validation.checkString("Enter room standards: ", "Room standards must be a string", "^[a-zA-Z]+$");
                int floorQuantity = Validation.checkInt("Enter floor quantity: ", "Floor quantity must be a positive number");
                house.setRoomStandards(roomStandards);
                house.setFloorQuantity(floorQuantity);

            }
            case Room room -> {
                String freeService = Validation.checkString("Enter free service: ", "Free service must be a string", "^[a-zA-Z]+$");
                room.setFreeServices(freeService);
            }
            default -> {
            }
        }
        String confirm = Validation.checkString("Do you want to add this facility? (Y/N): ", "Invalid input", "^[YyNn]$");
        if (confirm.equalsIgnoreCase("Y")) {
            facilityUsageMap.put(facility, 0);
            save();
        } else return;
    }
}
