package model.accomodation;

import model.Facility;

public class House extends Facility {
    private String roomStandards;
    private int floorQuantity;

    public House() {}

    public House(String serviceId, String serviceName, double areaUsage, double rentingPrice, int maxPeople, String rentingType, String roomStandards, int floorQuantity) {
        super(serviceId, serviceName, areaUsage, rentingPrice, maxPeople, rentingType);
        this.roomStandards = roomStandards;
        this.floorQuantity = floorQuantity;
    }

    public String getRoomStandards() {
        return roomStandards;
    }

    public void setRoomStandards(String roomStandards) {
        if (!Character.isUpperCase(roomStandards.charAt(0))) {
            throw new IllegalArgumentException("Room standard must start with an uppercase letter.");
        }
        this.roomStandards = roomStandards;
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
        return super.toString() + String.format(" %-15s %-10d", roomStandards, floorQuantity);
    }
}
