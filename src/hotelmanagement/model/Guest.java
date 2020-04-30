package hotelmanagement.model;

import hotelmanagement.exceptions.InvalidBookingException;
import hotelmanagement.model.abstractions.BaseAccommodation;

public class Guest {

    private int id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String citizenship;
    private String phone;
    private String email;

    public Guest(int id, String firstName, String lastName, String documentNumber,
                 String citizenship, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.citizenship = citizenship;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public void book(BaseAccommodation accommodation, BookingRequest bookingRequest) throws InvalidBookingException {
        accommodation.processBooking(bookingRequest);
    }

    @Override
    public String toString() {
        return "Vendég neve: " + getFullName() + ", okmányazonosító száma: "
                + documentNumber + ", állampolgársága: " + citizenship
                + ", telefonszáma: " + phone + ", e-mail címe: " + email + ".";
    }

}
