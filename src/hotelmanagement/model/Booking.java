package hotelmanagement.model;

import java.time.LocalDate;

public class Booking {

	private int id;
	private Guest guest;
	private Room room;
	private int numberOfGuests;
	private LocalDate arrivalDate;
	private int numberOfNights;
	private boolean paid;

	public Booking(Guest guest, Room room, int numberOfGuests, LocalDate arrivalDate, int numberOfNights,
			boolean paid) {
		this.guest = guest;
		this.room = room;
		this.numberOfGuests = numberOfGuests;
		this.numberOfNights = numberOfNights;
		this.arrivalDate = arrivalDate;
		this.paid = paid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setDate(LocalDate date) {
		this.arrivalDate = date;
	}

	public LocalDate getDepartureDate() {
		return arrivalDate.plusDays(numberOfNights);
	}

	public int getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(int numberOfNights) {
		this.numberOfNights = numberOfNights;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	@Override
	public String toString() {
		return "Foglalás azonosítója: " + id + ". Vendég neve: " + guest.getFullName() + ". Szobaszám: "
				+ room.getNumber() + ". Vendégek száma: " + numberOfGuests + " fő." + " Dátum: " + arrivalDate
				+ ". Pénzügyi státusz: " + (paid ? "fizetett" : "nem fizetett") + ".";
	}

}
