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

    public String getserviceId() {
        return serviceId;
    }

    public void setserviceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getAreaUsage() {
        return areaUsage;
    }

    public void setAreaUsage(double areaUsage) {
        this.areaUsage = areaUsage;
    }

    public double getRentingPrice() {
        return rentingPrice;
    }

    public void setRentingPrice(double rentingPrice) {
        this.rentingPrice = rentingPrice;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getRentingType() {
        return rentingType;
    }

    public void setRentingType(String rentingType) {
        this.rentingType = rentingType;
    }
    
    
}
