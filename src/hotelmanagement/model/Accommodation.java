package hotelmanagement.model;

import java.time.LocalDate;
import java.util.*;
import hotelmanagement.exceptions.InvalidBookingException;
import hotelmanagement.model.abstractions.BaseAccommodation;

public final class Accommodation extends BaseAccommodation {

    private String name;
    private String zipCode;
    private String city;
    private String address;
    private String phone;
    private String email;

    private List<Room> rooms;
    private List<Booking> bookings;

    public Accommodation(String name, String zipCode, String city,
                         String address, String phone, String email) {
        this.name = name;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.email = email;

        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    protected void confirmBookingRequest(BookingRequest bookingRequest) {
        int id = getNextBookingId();
        Guest guest = bookingRequest.getGuest();
        Room room = bookingRequest.getRoom();
        int numberOfGuests = bookingRequest.getNumberOfGuests();
        LocalDate arrivalDate = bookingRequest.getArrivalDate();
        boolean paid = false;

        while (arrivalDate.isBefore(bookingRequest.getDepartureDate())) {
            Booking booking = new Booking(id, guest, room, numberOfGuests, arrivalDate, paid);
            booking.getRoom().setReserved(true);

            bookings.add(booking);

            arrivalDate = arrivalDate.plusDays(1);
        }
    }

    @Override
    protected void validateBooking(BookingRequest bookingRequest) throws InvalidBookingException {
        if (!hasFreeRooms(bookingRequest.getArrivalDate())) {
            throw new InvalidBookingException("Teltház miatt nem lehetséges a foglalás!");
        }

        if (isRoomReserved(bookingRequest.getRoom().getNumber(), bookingRequest.getArrivalDate())) {
            throw new InvalidBookingException("A kiválasztott szoba sajnos foglalt!");
        }

        if (!areThereEnoughCapacity(bookingRequest.getRoom().getCapacity(), bookingRequest.getNumberOfGuests())) {
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
            if (booking.getDate().equals(arrivalDate) && booking.getRoom().getNumber() == roomNumber
                    && booking.getRoom().isReserved()) {
                return true;
            }
        }

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
            if (booking.getDate() == arrivalDate) {
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
    public String toString() {
        return "A szálláshely neve: " + name + ", címe: " + zipCode + " " + city + " " + address + "."
                + "Elérhetőségek: " + phone + ", " + email;
    }

}
