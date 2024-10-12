package model;

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

    public Facility() {}

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        if (!Character.isUpperCase(serviceName.charAt(0))) {
            throw new IllegalArgumentException("Service name must start with an uppercase letter.");
        }
        else {
            if (serviceId.startsWith("SVVL")) {
                this.serviceName = "Villa";
            } else if (serviceId.startsWith("SVHO")) {
                this.serviceName = "House";
            } else if (serviceId.startsWith("SVRO")) {
                this.serviceName = "Room";
            } else {
                throw new IllegalArgumentException("Invalid service ID prefix.");
            }
        }
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
        if (rentingType.equalsIgnoreCase("Year")) {
            this.rentingType = "Year";
        } else if (rentingType.equalsIgnoreCase("Month")) {
            this.rentingType = "Month";
        } else if (rentingType.equalsIgnoreCase("Day")) {
            this.rentingType = "Day";
        } else {
            throw new IllegalArgumentException("Invalid renting type.");
        }
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-10.2f %-15.2f %-10d %-15s",
                serviceId, serviceName, areaUsage, rentingPrice, maxPeople, rentingType);
    }
}
