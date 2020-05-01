package hotelmanagement.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import hotelmanagement.exceptions.InvalidBookingException;
import hotelmanagement.model.*;

public final class Accommodation extends BaseAccommodation {

	private String name;
	private String zipCode;
	private String city;
	private String address;
	private String phone;
	private String email;
	private List<Room> rooms;
	private List<Booking> bookings;

	public Accommodation() throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("accommodation.txt"), StandardCharsets.UTF_8);

		String[] data = lines.get(0).split(";");
		this.name = data[0];
		this.zipCode = data[1];
		this.city = data[2];
		this.address = data[3];
		this.phone = data[4];
		this.email = data[5];

		try {
			rooms = initializeRooms();
		} catch (Exception e) {
			throw e;
		}

		bookings = new ArrayList<>();
	}

	private List<Room> initializeRooms() throws IOException {
		List<Room> rooms = new ArrayList<>();
		List<String> roomLines = Files.readAllLines(Paths.get("rooms.txt"), StandardCharsets.UTF_8);

		for (String line : roomLines) {
			String[] data = line.split(";");

			int number = Integer.parseInt(data[0]);
			String name = data[1];
			int capacity = Integer.parseInt(data[2]);
			double price = Double.parseDouble(data[3]);

			rooms.add(new Room(number, name, capacity, price));
		}

		return rooms;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected void splitBookingIntoDays(Booking booking) {
		LocalDate arrivalDate = booking.getArrivalDate();
		int id = getNextBookingId();
		Guest guest = booking.getGuest();
		Room room = booking.getRoom();
		int numberOfGuests = booking.getNumberOfGuests();
		int numberOfNights = booking.getNumberOfNights();
		boolean paid = booking.isPaid();

		while (arrivalDate.isBefore(booking.getDepartureDate())) {
			Booking splittedBooking = new Booking(guest, room, numberOfGuests, arrivalDate, numberOfNights, paid);
			splittedBooking.setId(id);
			splittedBooking.getRoom().setReserved(true);

			bookings.add(splittedBooking);

			arrivalDate = arrivalDate.plusDays(1);
		}
	}

	@Override
	protected void validate(Booking booking) throws InvalidBookingException {

		if (isRoomReserved(booking.getRoom().getNumber(), booking.getArrivalDate())) {
			throw new InvalidBookingException("A kiválasztott szoba sajnos foglalt!");
		}

		if (!areThereEnoughCapacity(booking.getRoom().getCapacity(), booking.getNumberOfGuests())) {
			throw new InvalidBookingException("A vendégek száma meghaladja a szoba kapacitását!");
		}
	}

	private boolean areThereEnoughCapacity(int roomCapacity, int numberOfGuests) {
		if (numberOfGuests <= roomCapacity) {
			return true;
		}

		return false;
	}

	private boolean isRoomReserved(int roomNumber, LocalDate arrivalDate) {
		for (Booking booking : bookings) {
			if (booking.getArrivalDate().equals(arrivalDate) && booking.getRoom().getNumber() == roomNumber
					&& booking.getRoom().isReserved()) {
				return true;
			}
		}

		return false;
	}

	private int getNumberOfBookings(LocalDate arrivalDate) {
		return getBookingsByArrivalDate(arrivalDate).size();
	}

	private List<Booking> getBookingsByArrivalDate(LocalDate arrivalDate) {
		List<Booking> bookingsByArrivalDate = new ArrayList<Booking>();

		for (Booking booking : bookings) {
			if (booking.getArrivalDate() == arrivalDate) {
				bookingsByArrivalDate.add(booking);
			}
		}

		return bookingsByArrivalDate;
	}

	private int getNextBookingId() {
		return getMaxBookingId() + 1;
	}

	private int getMaxBookingId() {
		int max = 0;

		for (Booking booking : bookings) {
			if (booking.getId() > max) {
				max = booking.getId();
			}
		}

		return max;
	}

	@Override
	public List<Booking> getBookings() {
		return bookings;
	}

	@Override
	public void deleteBooking(int id) throws Exception {

		for (Booking booking : bookings) {
			if (booking.getId() == id) {
				bookings.removeIf(x -> x.getId() == id);
				return;
			}
		}
		throw new Exception("Nem található " + id + " azonosítóval foglalás. Nem lehet törölni!");
	}

	@Override
	public String toString() {
		return "A szálláshely neve: " + name + ", címe: " + zipCode + " " + city + " " + address + "."
				+ "Elérhetőségek: " + phone + ", " + email;
	}

	@Override
	public List<Room> getRooms() {
		return rooms;
	}

}
