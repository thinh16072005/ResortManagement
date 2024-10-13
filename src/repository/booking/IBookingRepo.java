package repository.booking;

import model.reservation.Booking;
import repository.Repository;

import java.io.File;
import java.util.TreeSet;

public interface IBookingRepo extends Repository<Booking, TreeSet<Booking>> {
    String bookingPath = "\\data\\booking.csv";

    @Override
    TreeSet<Booking> readfile();

    @Override
    void writeFile(TreeSet<Booking> entities);
}
