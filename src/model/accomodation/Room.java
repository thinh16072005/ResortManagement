package model.accomodation;

import model.Facility;

public class Room extends Facility {
    private String freeServices;

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

    
}
