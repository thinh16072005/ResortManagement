/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.booking;

import model.reservation.Booking;
import repository.booking.BookingRepo;

/**
 *
 * @author hungt
 */
public class BookingService extends BookingRepo implements IBookingService{

    @Override
    public Booking find(String entity) throws Exception {
        return null;
    }

    @Override
    public void display() {
        readfile();
        for (Booking booking : bookingList) {
            System.out.println(booking.toString());
        }
    }

    @Override
    public void add(Booking entity) {

    }

    @Override
    public void save() {

    }
}
