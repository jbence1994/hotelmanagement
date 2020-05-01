package hotelmanagement.service;

import hotelmanagement.exceptions.InvalidBookingException;
import hotelmanagement.model.*;

import java.util.List;

public abstract class BaseAccommodation {

	public abstract List<Booking> getBookings();

	public abstract String getName();

	protected abstract void validate(Booking booking) throws InvalidBookingException;

	protected abstract void splitBookingIntoDays(Booking booking);

	public abstract List<Room> getRooms();

	public void addBooking(Booking booking) throws InvalidBookingException {
		validate(booking);
		splitBookingIntoDays(booking);
	}

	public abstract void deleteBooking(int id) throws Exception;

}
