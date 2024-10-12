package model.accomodation;

import model.Facility;

public class Room extends Facility {
    private String freeServices;

    public Room() {}

    public Room(String serviceId, String serviceName, double areaUsage, double rentingPrice, int maxPeople, String rentingType, String freeServices) {
        super(serviceId, serviceName, areaUsage, rentingPrice, maxPeople, rentingType);
        this.freeServices = freeServices;
    }

    public String getFreeServices() {
        return freeServices;
    }

    public void setFreeServices(String freeServices) {
        this.freeServices = freeServices;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %-15s", freeServices);
    }
}
