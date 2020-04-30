package hotelmanagement.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import hotelmanagement.model.*;
import hotelmanagement.model.abstractions.BaseAccommodation;

public class Repository {

    private static List<Guest> guests = new ArrayList<>();

    private Repository() {
    }

    public static BaseAccommodation setupTestAccommodation() throws Exception {
        BaseAccommodation accommodation = initializeAccommodationData();
        processRoomsFile(accommodation);
        return accommodation;
    }

    public static Guest getTestGuest(int id) throws Exception {
        if (guests.isEmpty())
            processGuestsFile();

        return getGuestById(id);
    }

    private static BaseAccommodation initializeAccommodationData() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("accommodation.txt"),
                StandardCharsets.UTF_8);

        String[] data = lines.get(0).split(";");

        String name = data[0];
        String zipCode = data[1];
        String city = data[2];
        String address = data[3];
        String phone = data[4];
        String email = data[5];

        return new Accommodation(name, zipCode, city, address, phone, email);
    }

    private static void processRoomsFile(BaseAccommodation accommodation) throws IOException {
        List<String> roomLines = Files.readAllLines(Paths.get("rooms.txt"),
                StandardCharsets.UTF_8);

        for (String line : roomLines) {
            String[] data = line.split(";");

            int number = Integer.parseInt(data[0]);
            String name = data[1];
            int capacity = Integer.parseInt(data[2]);
            double price = Double.parseDouble(data[3]);

            Room room = new Room(number, name, capacity, price);

            accommodation.addRoom(room);
        }
    }

    private static void processGuestsFile() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("guests.txt"), StandardCharsets.UTF_8);

        for (String line : lines) {
            String[] data = line.split(";");

            int id = Integer.parseInt(data[0]);
            String firstName = data[1];
            String lastName = data[2];
            String documentNumber = data[3];
            String citizenship = data[4];
            String phone = data[5];
            String email = data[6];

            guests.add(new Guest(id, firstName, lastName, documentNumber, citizenship, phone, email));
        }
    }

    private static Guest getGuestById(int id) throws Exception {
        for (Guest guest : guests) {
            if (guest.getId() == id)
                return guest;

        }

        throw new Exception("Nincs a listában objektum a következő azonosítóval: " + id);
    }

}
