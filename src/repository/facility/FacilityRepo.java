package repository.facility;

import model.Facility;
import model.accomodation.House;
import model.accomodation.Room;
import model.accomodation.Villa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class FacilityRepo implements IFacilityRepo {

    protected LinkedHashMap<Facility, Integer> facilityUsageMap = new LinkedHashMap<>();
    @Override
    public LinkedHashMap<Facility, Integer> readFile() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path + facilityPath))) {

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                double areaUsage = Double.parseDouble(parts[2]);
                double rentingPrice = Double.parseDouble(parts[3]);
                int maxPeople = Integer.parseInt(parts[4]);
                int usageCount = 0;

                Facility facility;
                if (parts[0].startsWith("SVVL")) {
                    facility = new Villa(parts[0], parts[1], areaUsage, rentingPrice, maxPeople, parts[5], parts[6], Double.parseDouble(parts[7]), Integer.parseInt(parts[8]));
                }
                else if (parts[0].startsWith("SVHO")) {
                    facility = new House(parts[0], parts[1], areaUsage, rentingPrice, maxPeople, parts[5], parts[6], Integer.parseInt(parts[7]));
                }
                else if (parts[0].startsWith("SVRO")) {
                    facility = new Room(parts[0], parts[1], areaUsage, rentingPrice, maxPeople, parts[5], parts[6]);
                } else {
                    throw new IllegalArgumentException("Invalid service ID prefix.");
                }
                facilityUsageMap.put(facility, usageCount);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return facilityUsageMap;
    }

    @Override
    public void writeFile(LinkedHashMap<Facility, Integer> facility) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + facilityPath, true))) {
            for (Map.Entry<Facility, Integer> entry : facility.entrySet()) {
                Facility fac = entry.getKey();
                int usageCount = entry.getValue();
                String line;
                switch (fac) {
                    case Villa villa -> line = String.format("%s,%s,%.2f,%.2f,%d,%s,%s,%f,%d,%d",
                            villa.getServiceId(),
                            villa.getServiceName(),
                            villa.getAreaUsage(),
                            villa.getRentingPrice(),
                            villa.getMaxPeople(),
                            villa.getRentingType(),
                            villa.getRoomStandards(),
                            villa.getPoolArea(),
                            villa.getFloorQuantity(),
                            usageCount);
                    case House house -> line = String.format("%s,%s,%.2f,%.2f,%d,%s,%s,%d,%d",
                            house.getServiceId(),
                            house.getServiceName(),
                            house.getAreaUsage(),
                            house.getRentingPrice(),
                            house.getMaxPeople(),
                            house.getRentingType(),
                            house.getRoomStandards(),
                            house.getFloorQuantity(),
                            usageCount);
                    case Room room -> line = String.format("%s,%s,%f,%f,%d,%s,%s,%d",
                            room.getServiceId(),
                            room.getServiceName(),
                            room.getAreaUsage(),
                            room.getRentingPrice(),
                            room.getMaxPeople(),
                            room.getRentingType(),
                            room.getFreeServices(),
                            usageCount);
                    default -> throw new IllegalArgumentException("Unknown facility type.");
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
