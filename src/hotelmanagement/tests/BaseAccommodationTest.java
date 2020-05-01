package hotelmanagement.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import hotelmanagement.model.*;
import hotelmanagement.service.Accommodation;
import hotelmanagement.service.BaseAccommodation;

class BaseAccommodationTest {

	@Test
	public void test_addBooking_has_enough_booking_added() {

		try {
			System.out.println("test_addBooking_has_enough_booking_added");
			BaseAccommodation testAccommodation = new Accommodation();
			Room testRoom = testAccommodation.getRooms().get(0);
			Guest testGuest = new Guest(1, "Juhász", "Bence", "ABC123", "magyar", "06202944280",
					"juhasz.bence.zsolt@gmail.com");
			Booking booking = new Booking(1, testGuest, testRoom, 2, LocalDate.of(2020, 05, 02), 2, false);

			testAccommodation.addBooking(booking);

			int expectedNumberOfBookings = 2;
			int actualNumberOfBookings = testAccommodation.getBookings().size();

			assertEquals(expectedNumberOfBookings, actualNumberOfBookings);

		} catch (Exception e) {
		}

		System.out.println("Sikeresen hozzáadja a foglalást napra lebontva új objektumokként.");
		System.out.println("");
	}

	@Test
	public void test_accommodation_successfully_initialized_with_rooms_from_file() {

		try {
			System.out.println("test_accommodation_successfully_initialized_with_rooms_from_file");
			BaseAccommodation testAccommodation = new Accommodation();
			int expectedNumberOfRooms = 10;
			int acutalNumberOfRooms = testAccommodation.getRooms().size();

			assertEquals(expectedNumberOfRooms, acutalNumberOfRooms);
		} catch (Exception e) {
		}

		System.out.println("Sikeresen feltölti a szálláshelyet az összes szobával a fájlból.");
		System.out.println("");
	}

	@Test
	public void test_getDepartureDate_is_correct() {

		try {
			System.out.println("test_getDepartureDate_is_correct");
			BaseAccommodation testAccommodation = new Accommodation();
			Room testRoom = testAccommodation.getRooms().get(0);
			Guest testGuest = new Guest(1, "Juhász", "Bence", "ABC123", "magyar", "06202944280",
					"juhasz.bence.zsolt@gmail.com");
			Booking booking = new Booking(1, testGuest, testRoom, 2, LocalDate.of(2020, 05, 02), 2, false);

			LocalDate expectedDepartureDate = LocalDate.of(2020, 05, 04);
			LocalDate actualDepartureDate = booking.getDepartureDate();

			assertEquals(expectedDepartureDate, actualDepartureDate);

		} catch (Exception e) {
		}

		System.out.println("Sikeresen kiszámolja az algoritmus a távozás dátumát.");
		System.out.println("");

	}

	@Test
	public void test_is_room_reserved_should_throw_exception() {

		try {
			System.out.println("test_is_room_reserved_should_throw_exception");
			BaseAccommodation testAccommodation = new Accommodation();

			Room testRoom = testAccommodation.getRooms().get(0);

			Guest testGuest1 = new Guest(1, "Juhász", "Bence", "ABC123", "magyar", "06702344280",
					"juhasz.bence@teszt.hu");

			Guest testGuest2 = new Guest(1, "Deim", "Máté", "XYZ789", "magyar", "06502913280", "deim.mate@teszt.hu");

			Booking booking1 = new Booking(1, testGuest1, testRoom, 2, LocalDate.of(2020, 05, 02), 3, false);
			testAccommodation.addBooking(booking1);

			Booking booking2 = new Booking(1, testGuest2, testRoom, 2, LocalDate.of(2020, 05, 03), 1, false);
			testAccommodation.addBooking(booking2);

			fail("Átmegy a teszt túlfoglalás esetén!");
		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());
		}

		System.out.println("Sikeresen elszáll a program túlfoglalás esetén.");
		System.out.println();

	}

	@Test
	public void test_are_there_enough_capacity_in_case_number_of_guests_higher_than_room_capacity_should_throw_exception() {

		try {
			System.out.println(
					"test_are_there_enough_capacity_in_case_number_of_guests_higher_than_room_capacity_should_throw_exception");
			BaseAccommodation testAccommodation = new Accommodation();
			Room testRoom = testAccommodation.getRooms().get(0);
			Guest testGuest1 = new Guest(1, "Juhász", "Bence", "ABC123", "magyar", "06702344280",
					"juhasz.bence@teszt.hu");

			Booking booking = new Booking(1, testGuest1, testRoom, 10, LocalDate.of(2020, 06, 01), 1, false);
			testAccommodation.addBooking(booking);

			fail("Nem dob kivételt a függvény, ha nagyobb vendégszámmal foglalok, mint a szoba kapacitása.");

		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());
		}
		System.out.println("Sikeresen elszáll a program, ha nagyobb vendégszámmal foglalok, mint a szoba kapacitása.");
		System.out.println();
	}

	@Test
	public void test_book_on_same_room_but_other_date_out_of_another_bookings_interval_should_not_throw_exception() {

		try {
			System.out.println(
					"test_book_on_same_room_but_other_date_out_of_another_bookings_interval_should_not_throw_exception");
			BaseAccommodation testAccommodation = new Accommodation();

			Room testRoom = testAccommodation.getRooms().get(0);

			Guest testGuest1 = new Guest(1, "Juhász", "Bence", "ABC123", "magyar", "06702344280",
					"juhasz.bence@teszt.hu");

			Booking booking1 = new Booking(1, testGuest1, testRoom, 2, LocalDate.of(2020, 05, 02), 3, false);
			testAccommodation.addBooking(booking1);

			Guest testGuest2 = new Guest(1, "Deim", "Máté", "XYZ789", "magyar", "06502913280", "deim.mate@teszt.hu");

			Booking booking2 = new Booking(1, testGuest2, testRoom, 2, LocalDate.of(2020, 06, 02), 1, false);
			testAccommodation.addBooking(booking2);
		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());
		}

		System.out.println(
				"Sikeresen a foglalás, ha a második foglalás ugyanarra a szobára történik, de nem nyúlik bele más foglalás intervallumába (ugyanarra a szobára).");
		System.out.println();

	}

	@Test
	public void test_in_case_of_full_house_should_throw_exception() {
		try {
			System.out.println("test_in_case_of_full_house_should_throw_exception");

			BaseAccommodation testAccommodation = new Accommodation();

			Room testRoom = testAccommodation.getRooms().get(0);

			Guest testGuest1 = new Guest(1, "Juhász", "Bence", "ABC123", "magyar", "06702344280",
					"juhasz.bence@teszt.hu");

			Booking booking1 = new Booking(1, testGuest1, testRoom, 2, LocalDate.of(2020, 05, 02), 3, false);
			testAccommodation.addBooking(booking1);

			Guest testGuest2 = new Guest(1, "Deim", "Máté", "XYZ789", "magyar", "06502913280", "deim.mate@teszt.hu");

			Booking booking2 = new Booking(1, testGuest2, testRoom, 2, LocalDate.of(2020, 06, 02), 1, false);
			testAccommodation.addBooking(booking2);
			fail("TÖRÖLD KI HA KÉSZ A TESZTESET FEÁLLÍTÁSA");
		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());

		}
		System.out.println("Sikeresen elszáll a program, ha telitházra foglalok.");
		System.out.println();
	}

}
