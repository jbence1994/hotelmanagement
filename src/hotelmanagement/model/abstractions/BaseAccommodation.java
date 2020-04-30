package hotelmanagement.model.abstractions;

import hotelmanagement.exceptions.InvalidBookingException;
import hotelmanagement.model.*;

import java.util.List;

public abstract class BaseAccommodation {

    public abstract String getName();

    public abstract List<Room> getRooms();

    public abstract void addRoom(Room room);

    protected abstract void validateBooking(BookingRequest bookingRequest) throws InvalidBookingException;

    protected abstract void confirmBookingRequest(BookingRequest bookingRequest);

    public void processBooking(BookingRequest bookingRequest) throws InvalidBookingException {
        validateBooking(bookingRequest);
        confirmBookingRequest(bookingRequest);
    }

}
