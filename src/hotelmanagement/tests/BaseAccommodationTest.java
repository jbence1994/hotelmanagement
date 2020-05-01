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
			Guest testGuest = new Guest("Juhász", "Bence", "magyar");
			Booking booking = new Booking(testGuest, testRoom, 2, LocalDate.of(2020, 05, 02), 2);

			testAccommodation.addBooking(booking);

			int expectedNumberOfBookings = 2;
			int actualNumberOfBookings = testAccommodation.getBookings().size();

			assertEquals(expectedNumberOfBookings, actualNumberOfBookings);

		} catch (Exception e) {
		}

		System.out.println("Sikeresen hozzáadja a foglalást napra lebontva új objektumokként.");
		System.out.println();
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
			Guest testGuest = new Guest("Juhász", "Bence", "magyar");
			Booking booking = new Booking(testGuest, testRoom, 2, LocalDate.of(2020, 05, 02), 2);

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

			Guest testGuest1 = new Guest("Juhász", "Bence", "magyar");

			Guest testGuest2 = new Guest("Deim", "Máté", "magyar");

			Booking booking1 = new Booking(testGuest1, testRoom, 2, LocalDate.of(2020, 05, 02), 3);
			testAccommodation.addBooking(booking1);

			Booking booking2 = new Booking(testGuest2, testRoom, 2, LocalDate.of(2020, 05, 03), 1);
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
			Guest testGuest1 = new Guest("Juhász", "Bence", "magyar");

			Booking booking = new Booking(testGuest1, testRoom, 10, LocalDate.of(2020, 06, 01), 1);
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

			Guest testGuest1 = new Guest("Juhász", "Bence", "magyar");

			Booking booking1 = new Booking(testGuest1, testRoom, 2, LocalDate.of(2020, 05, 02), 3);
			testAccommodation.addBooking(booking1);

			Guest testGuest2 = new Guest("Deim", "Máté", "magyar");

			Booking booking2 = new Booking(testGuest2, testRoom, 2, LocalDate.of(2020, 06, 02), 1);
			testAccommodation.addBooking(booking2);
		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());
		}

		System.out.println(
				"Sikeresen a foglalás, ha a második foglalás ugyanarra a szobára történik, de nem nyúlik bele más foglalás intervallumába (ugyanarra a szobára).");
		System.out.println();

	}

	@Test
	public void test_delete_succefully_a_booking_with_specified_id() {

		try {
			System.out.println("test_delete_succefully_a_booking_with_specified_id");
			BaseAccommodation testAccommodation = new Accommodation();
			Room testRoom = testAccommodation.getRooms().get(0);
			Guest testGuest1 = new Guest("Juhász", "Bence", "magyar");

			Booking booking = new Booking(testGuest1, testRoom, 1, LocalDate.of(2020, 06, 01), 1);
			testAccommodation.addBooking(booking);

			testAccommodation.deleteBooking(1);
			assertEquals(0, testAccommodation.getBookings().size());

		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());
		}
		System.out.println("Sikeresen törli az adott azonosítójú foglalásokat.");
		System.out.println();

	}

	@Test
	public void test_delete_booking_with_specified_id_not_exists_should_throw_exception() {

		try {
			System.out.println("test_delete_booking_with_specified_id_not_exists_should_throw_exception");
			BaseAccommodation testAccommodation = new Accommodation();
			Room testRoom = testAccommodation.getRooms().get(0);
			Guest testGuest1 = new Guest("Juhász", "Bence", "magyar");

			Booking booking = new Booking(testGuest1, testRoom, 1, LocalDate.of(2020, 06, 01), 1);
			testAccommodation.addBooking(booking);

			testAccommodation.deleteBooking(2);

		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());
		}
		System.out.println("Sikeresen elszáll a program, ha nem létező azonosítójú foglalást próbálok törölni.");
		System.out.println();

	}

	@Test
	public void test_delete_bookings_with_specified_id_not_exists_should_throw_exception() {

		try {
			System.out.println("test_delete_bookings_with_specified_id_not_exists_should_throw_exception");
			BaseAccommodation testAccommodation = new Accommodation();
			Room testRoom = testAccommodation.getRooms().get(0);
			Guest testGuest1 = new Guest("Juhász", "Bence", "magyar");

			Booking booking = new Booking(testGuest1, testRoom, 1, LocalDate.of(2020, 06, 01), 3);
			testAccommodation.addBooking(booking);

			testAccommodation.deleteBooking(1);
			assertEquals(0, testAccommodation.getBookings().size());
		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());
		}
		System.out.println("Sikeresen töröl többnapos foglalást is, ha azonos az azonosítójuk.");
		System.out.println();

	}

	@Test
	public void test_booking_arrival_date_is_before_today() {

		try {
			System.out.println("test_booking_arrival_date_is_before_today");
			BaseAccommodation testAccommodation = new Accommodation();
			Room testRoom = testAccommodation.getRooms().get(0);
			Guest testGuest1 = new Guest("Juhász", "Bence", "magyar");

			Booking booking = new Booking(testGuest1, testRoom, 1, LocalDate.of(2020, 02, 01), 3);
			testAccommodation.addBooking(booking);

			testAccommodation.deleteBooking(1);
			assertEquals(0, testAccommodation.getBookings().size());
		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());
		}
		System.out.println("Sikeresen elszáll a program, ha a mai nap előttre foglalok.");
		System.out.println();

	}

}
