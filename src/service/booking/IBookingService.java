package service.booking;

import model.reservation.Booking;
import service.Service;

public interface IBookingService extends Service<Booking> {
    @Override
    Booking find(String entity) throws Exception;

    @Override
    void display();

    @Override
    void add(Booking entity);

    @Override
    void save();
}
