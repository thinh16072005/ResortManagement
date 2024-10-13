package repository.booking;

import model.person.Customer;
import model.reservation.Booking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class BookingRepo implements IBookingRepo {
    protected TreeSet<Booking> bookingList = new TreeSet<>();
    @Override
    public TreeSet<Booking> readfile() {
        String line;
        try {
            BufferedReader input = new BufferedReader(new FileReader(path + bookingPath));

            while ((line = input.readLine()) != null) {
                String[] tokString = line.split(",");
                Date bookingDate = new SimpleDateFormat("dd/MM/yyyy").parse(tokString[1]);
                Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(tokString[2]);
                Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(tokString[3]);
                Booking booking = new Booking(tokString[0], bookingDate, startDate, endDate, tokString[4], tokString[5]);
                bookingList.add(booking);
            }
            return bookingList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeFile(TreeSet<Booking> bookings) {
    }
    
}
