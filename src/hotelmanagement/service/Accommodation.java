package hotelmanagement.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import hotelmanagement.exceptions.InvalidBookingException;
import hotelmanagement.model.Booking;
import hotelmanagement.model.Guest;
import hotelmanagement.model.Room;

public final class Accommodation extends BaseAccommodation {

	private static BaseAccommodation accommodation;

	private String name;
	private String zipCode;
	private String city;
	private String address;
	private String phone;
	private String email;
	private List<Room> rooms;
	private List<Booking> bookings;

	private Accommodation(String name, String zipCode, String city, String address, String phone, String email)
			throws Exception {
		this.name = name;
		this.zipCode = zipCode;
		this.city = city;
		this.address = address;
		this.phone = phone;
		this.email = email;

		try {
			rooms = initializeRooms();
		} catch (Exception e) {
			throw e;
		}

		bookings = new ArrayList<>();
	}

	private static BaseAccommodation initializeAccommodation() throws Exception {

		List<String> lines = Files.readAllLines(Paths.get("accommodation.txt"), StandardCharsets.UTF_8);

		String[] data = lines.get(0).split(";");
		String name = data[0];
		String zipCode = data[1];
		String city = data[2];
		String address = data[3];
		String phone = data[4];
		String email = data[5];

		return new Accommodation(name, zipCode, city, address, phone, email);
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

	public static BaseAccommodation getAccommodation() throws Exception {

		if (accommodation == null) {
			accommodation = initializeAccommodation();
			return accommodation;
		}

		return accommodation;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected void confirm(Booking booking) {
		LocalDate arrivalDate = booking.getArrivalDate();

		while (arrivalDate.isBefore(booking.getDepartureDate())) {
			booking.getRoom().setReserved(true);
			bookings.add(booking);
			arrivalDate = arrivalDate.plusDays(1);
		}
	}

	@Override
	protected void validate(Booking booking) throws InvalidBookingException {
		if (!hasFreeRooms(booking.getArrivalDate())) {
			throw new InvalidBookingException("Teltház miatt nem lehetséges a foglalás!");
		}

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
		} // TODO: nem jól működik ...

		return false;
	}

	private boolean hasFreeRooms(LocalDate arrivalDate) {
		boolean hasFreeRooms = false;

		if (getNumberOfBookings(arrivalDate) != rooms.size()) {
			hasFreeRooms = true;
		}

		return hasFreeRooms;
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
				bookings.remove(booking);
				return;
			}
		}
		throw new Exception("Nincs foglalás az alábbi azonosítóval: " + id + ". Nem lehet törölni!");

	}

	@Override
	public String toString() {
		return "A szálláshely neve: " + name + ", címe: " + zipCode + " " + city + " " + address + "."
				+ "Elérhetőségek: " + phone + ", " + email;
	}

	@Override
	public Booking getBookingById(int id) throws Exception {
		for (Booking booking : bookings) {
			if (booking.getId() == id) {
				return booking;
			}
		}
		throw new Exception("Nem található foglalás az alábbi azonosítóval: " + id);
	}

	@Override

	public List<Room> getRooms() {
		return rooms;
	}

}
