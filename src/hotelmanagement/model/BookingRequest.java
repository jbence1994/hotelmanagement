package hotelmanagement.model;

import java.time.LocalDate;

public class BookingRequest {

    private Guest guest;
    private Room room;
    private int numberOfGuests;
    private LocalDate arrivalDate;
    private int numberOfNights;

    public BookingRequest(Guest guest, Room room, int numberOfGuests, LocalDate arrivalDate, int numberOfNights) {
        this.guest = guest;
        this.room = room;
        this.numberOfGuests = numberOfGuests;
        this.arrivalDate = arrivalDate;
        this.numberOfNights = numberOfNights;
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

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public LocalDate getDepartureDate() {
        return arrivalDate.plusDays(numberOfNights);
    }

    @Override
    public String toString() {
        return "Foglalási igény:\nVendég neve: " + guest.getFullName()
                + ", szobaszám: " + room.getNumber()
                + ", fő: " + numberOfGuests + ". Érkezés dátuma: "
                + arrivalDate + ", távozás dátuma: " + getDepartureDate()
                + " (éjszakák száma: " + numberOfNights
                + " éj).";
    }

}
