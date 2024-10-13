package model.reservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Booking implements Comparable<Booking> {
    private String bookingId;
    private String customerId;
    private String serviceId;
    private Date bookingDate, startDate, endDate;

    public Booking(String bookingId, Date bookingDate, Date startDate, Date endDate, String customerId, String serviceId) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.serviceId = serviceId;
        setBookingDate(bookingDate);
        setStartDate(startDate);
        setEndDate(endDate);
    }
    
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        if (!bookingId.equals("^\\BKd{1}+$")) {
            System.err.println("Invalid booking ID. Must be in the format BKd.");
        }
        else {this.bookingId = bookingId;}
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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("""
                        | %-15s | %-20s |
                        +-----------------+----------------------+
                        | %-15s | %-20s |
                        +-----------------+----------------------+
                        | %-15s | %-20s |
                        | %-15s | %-20s |
                        | %-15s | %-20s |
                        | %-15s | %-20s |
                        | %-15s | %-20s |
                        +-----------------+----------------------+
                        """,
                "Field", "Value",
                "Booking ID", getBookingId(),
                "Booking date:", sdf.format(getBookingDate()),
                "Start date", sdf.format(getStartDate()),
                "End date", sdf.format(getEndDate()),
                "Customer ID", getCustomerId(),
                "Service ID", getServiceId()
        );
    }

    @Override
    public int compareTo(Booking o) {
        int startDateComparison = this.startDate.compareTo(o.startDate);
        if (startDateComparison != 0) {
            return startDateComparison;
        }
        return this.endDate.compareTo(o.endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }
}
