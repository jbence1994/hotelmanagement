package hotelmanagement.service;

import hotelmanagement.exceptions.InvalidBookingException;
import hotelmanagement.model.*;

import java.util.List;

public abstract class BaseAccommodation {

	public abstract List<Booking> getBookings();

	public abstract String getName();

	protected abstract void validate(Booking booking) throws InvalidBookingException;

	protected abstract void confirm(Booking booking);

	public void addBooking(Booking booking) throws InvalidBookingException {
		validate(booking);
		confirm(booking);
	}

	public void updateBooking(Booking booking) throws InvalidBookingException {
		validate(booking);
		confirm(booking);
	}

	public abstract void deleteBooking(int id) throws Exception;

	public abstract Booking getBookingById(int id) throws Exception;

}
