package model.accomodation;

import model.Facility;

public class Villa extends Facility {
    private String roomStandards;
    private double poolArea;
    private int floorQuantity;

    public Villa() {}

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
        if (poolArea < 30) {
            throw new IllegalArgumentException("Pool area must be greater than 30.");
        }
        this.poolArea = poolArea;
    }

    public int getFloorQuantity() {
        return floorQuantity;
    }

    public void setFloorQuantity(int floorQuantity) {
        if (floorQuantity <= 0) {
            throw new IllegalArgumentException("Floor quantity must be greater than 0.");
        }
        this.floorQuantity = floorQuantity;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %-15s %-10.2f %-10d", roomStandards, poolArea, floorQuantity);
    }
    
}
