package model.accomodation;

import model.Facility;

public class Villa extends Facility {
    private String roomStandards;
    private double poolArea;
    private int floorQuantity;

    public Villa(String serviceId, String serviceName, double areaUsage, double rentingPrice, int maxPeople, String rentingType, String roomStandards, double poolArea, int floorQuantity) {
        super(serviceId, serviceName, areaUsage, rentingPrice, maxPeople, rentingType);
        this.roomStandards = roomStandards;
        this.poolArea = poolArea;
        this.floorQuantity = floorQuantity;
    }

    public String getRoomStandards() {
        return roomStandards;
    }

    public void setRoomStandards(String roomStandards) {
        this.roomStandards = roomStandards;
    }

    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }

    public int getFloorQuantity() {
        return floorQuantity;
    }

    public void setFloorQuantity(int floorQuantity) {
        this.floorQuantity = floorQuantity;
    }

    
}
