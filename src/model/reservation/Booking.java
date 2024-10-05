package model.reservation;

import java.time.LocalDate;

public class Booking {
    private String bookingId;
    private String customerId;
    private String serviceId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(String bookingId, String customerId, String serviceId, LocalDate startDate, LocalDate endDate) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    
}
