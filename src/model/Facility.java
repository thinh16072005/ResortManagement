package model;

import model.accomodation.House;
import model.accomodation.Room;
import model.accomodation.Villa;

public abstract class Facility {
    protected String serviceId;
    protected String serviceName;
    protected double areaUsage;
    protected double rentingPrice;
    protected int maxPeople;
    protected String rentingType;

    public Facility(String serviceId, String serviceName, double areaUsage, double rentingPrice, int maxPeople, String rentingType) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.areaUsage = areaUsage;
        this.rentingPrice = rentingPrice;
        this.maxPeople = maxPeople;
        this.rentingType = rentingType;
    }

    public String getserviceId() {
        return serviceId;
    }

    public void setserviceId(String serviceId) {
        String regex = "";
        if (this instanceof Villa) {
            regex = "^SVVL-[0-9]{4}$";
        } else if (this instanceof House) {
            regex = "^SVHO-[0-9]{4}$";
        } else if (this instanceof Room) {
            regex = "^SVRO-[0-9]{4}$";
        }
        if (!serviceId.matches(regex)) {
            throw new IllegalArgumentException("Service ID must be in format SVXX-YYYY with X is V, H, R and Y is 4 digits");
        }
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        if (!Character.isUpperCase(serviceName.charAt(0))) {
            throw new IllegalArgumentException("Service name must start with an uppercase letter.");
        }
        this.serviceName = serviceName;
    }

    public double getAreaUsage() {
        return areaUsage;
    }

    public void setAreaUsage(double areaUsage) {
        if (areaUsage <= 30) {
            throw new IllegalArgumentException("Area usage must be greater than 30.");
        }
        this.areaUsage = areaUsage;
    }

    public double getRentingPrice() {
        return rentingPrice;
    }

    public void setRentingPrice(double rentingPrice) {
        if (rentingPrice <= 0) {
            throw new IllegalArgumentException("Renting price must be greater than 0.");
        }
        this.rentingPrice = rentingPrice;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        if (maxPeople <= 0 || maxPeople >= 20) {
            throw new IllegalArgumentException("Max people must be between 0 and 20.");
        }
        this.maxPeople = maxPeople;
    }

    public String getRentingType() {
        return rentingType;
    }

    public void setRentingType(String rentingType) {
        if (!Character.isUpperCase(rentingType.charAt(0))) {
            throw new IllegalArgumentException("Renting type must start with an uppercase letter.");
        }
        this.rentingType = rentingType;
    }
    
    
}
