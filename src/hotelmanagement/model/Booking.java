package hotelmanagement.model;

import java.time.LocalDate;

public class Booking {

    private int id;
    private Guest guest;
    private Room room;
    private int numberOfGuests;
    private LocalDate date;
    private boolean paid;

    public Booking(int id, Guest guest, Room room, int numberOfGuests, LocalDate date, boolean paid) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.numberOfGuests = numberOfGuests;
        this.date = date;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Foglalás azonosítója: " + id + ". Vendég neve: "
                + guest.getFullName() + ". Szobaszám: " + room.getNumber()
                + ". Vendégek száma: " + numberOfGuests + " fő." + " Dátum: "
                + date + ". Pénzügyi státusz: "
                + (paid ? "fizetett" : "nem fizetett") + ".";
    }

}
