package model.accomodation;

import model.Facility;

public class House extends Facility {
    private String roomStandards;
    private int floorQuantity;

    public House(String serviceId, String serviceName, double areaUsage, double rentingPrice, int maxPeople, String rentingType, String roomStandards, int floorQuantity) {
        super(serviceId, serviceName, areaUsage, rentingPrice, maxPeople, rentingType);
        this.roomStandards = roomStandards;
        this.floorQuantity = floorQuantity;
    }

    public String getRoomStandards() {
        return roomStandards;
    }

    public void setRoomStandards(String roomStandards) {
        this.roomStandards = roomStandards;
    }

    public int getFloorQuantity() {
        return floorQuantity;
    }

    public void setFloorQuantity(int floorQuantity) {
        this.floorQuantity = floorQuantity;
    }
    
    
}
