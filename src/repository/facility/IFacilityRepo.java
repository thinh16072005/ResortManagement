package repository.facility;

import model.Facility;
import repository.Repository;

import java.util.LinkedHashMap;

public interface IFacilityRepo extends Repository<Facility, LinkedHashMap<Facility, Integer>> {
    String facilityPath = "\\data\\facility.csv";

    LinkedHashMap<Facility, Integer> readFile();

    void writeFile(LinkedHashMap<Facility, Integer> facility);
}
