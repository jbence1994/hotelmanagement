package hotelmanagement.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import hotelmanagement.model.*;
import hotelmanagement.service.Accommodation;
import hotelmanagement.service.BaseAccommodation;

class BaseAccommodationTest {

	@Test
	public void test_addBooking_enough_booking_added() {

		try {
			System.out.println("test_addBooking_enough_booking_added()");
			BaseAccommodation testAccommodation = Accommodation.getAccommodation();
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

	}

	@Test
	public void test_testAccommodation_initialized_with_rooms_from_file() {

		try {
			System.out.println("test_testAccommodation_initialized_with_rooms_from_file()");
			BaseAccommodation testAccommodation = Accommodation.getAccommodation();
			int expectedNumberOfRooms = 10;
			int acutalNumberOfRooms = testAccommodation.getRooms().size();

			assertEquals(expectedNumberOfRooms, acutalNumberOfRooms);
		} catch (Exception e) {

		}

	}

	@Test
	public void test_getDepartureDate_correct() {

		try {
			System.out.println("test_getDepartureDate_correct()");
			BaseAccommodation testAccommodation = Accommodation.getAccommodation();
			Room testRoom = testAccommodation.getRooms().get(0);
			Guest testGuest = new Guest(1, "Juhász", "Bence", "ABC123", "magyar", "06202944280",
					"juhasz.bence.zsolt@gmail.com");
			Booking booking = new Booking(1, testGuest, testRoom, 2, LocalDate.of(2020, 05, 02), 2, false);

			LocalDate expectedDepartureDate = LocalDate.of(2020, 05, 04);
			LocalDate actualDepartureDate = booking.getDepartureDate();

			assertEquals(expectedDepartureDate, actualDepartureDate);

		} catch (Exception e) {

		}

	}

	@Test
	public void test_overbook_should_throw_exception() {

		try {
			System.out.println("test_overbook_should_throw_exception()");
			BaseAccommodation testAccommodation = Accommodation.getAccommodation();

			Room testRoom = testAccommodation.getRooms().get(0);

			Guest testGuest1 = new Guest(1, "Juhász", "Bence", "ABC123", "magyar", "06702344280",
					"juhasz.bence@teszt.hu");

			Guest testGuest2 = new Guest(1, "Deim", "Máté", "XYZ789", "magyar", "06502913280", "deim.mate@teszt.hu");

			Booking booking1 = new Booking(1, testGuest1, testRoom, 2, LocalDate.of(2020, 05, 02), 2, false);
			testAccommodation.addBooking(booking1);

			Booking booking2 = new Booking(1, testGuest2, testRoom, 2, LocalDate.of(2020, 05, 03), 1, false);
			testAccommodation.addBooking(booking2);

			fail("Átmegy a teszt túlfoglalás esetén!");
		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());
			return;
		}

	}

	@Test
	public void test_room_is_reserved_when_not_reserved_should_not_throw_exception() {

		try {
			System.out.println("test_overbook_should_throw_exception()");
			BaseAccommodation testAccommodation = Accommodation.getAccommodation();

			Room testRoom = testAccommodation.getRooms().get(0);

			Guest testGuest1 = new Guest(1, "Juhász", "Bence", "ABC123", "magyar", "06702344280",
					"juhasz.bence@teszt.hu");

			Guest testGuest2 = new Guest(1, "Deim", "Máté", "XYZ789", "magyar", "06502913280", "deim.mate@teszt.hu");

			Booking booking1 = new Booking(1, testGuest1, testRoom, 2, LocalDate.of(2020, 05, 02), 2, false);
			testAccommodation.addBooking(booking1);

			Booking booking2 = new Booking(1, testGuest2, testRoom, 2, LocalDate.of(2020, 05, 03), 1, false);
			testAccommodation.addBooking(booking2);

			fail("Átmegy a teszt túlfoglalás esetén!");
		} catch (Exception e) {
			System.out.println("Dobott kivételt: " + e.getMessage());
			return;
		}

	}

	@Test
	public void test_number_of_guests_higher_than_room_capacity_should_throw_exception() {

		BaseAccommodation testAccommodation;
		try {
			testAccommodation = Accommodation.getAccommodation();

			Room testRoom = testAccommodation.getRooms().get(0);

			Guest testGuest1 = new Guest(1, "Juhász", "Bence", "ABC123", "magyar", "06702344280",
					"juhasz.bence@teszt.hu");

			Booking booking = new Booking(1, testGuest1, testRoom, 10, LocalDate.of(2020, 06, 01), 1, false);
			testAccommodation.addBooking(booking);

			fail("Nem dob kivételt a függvény, ha nagyobb vendégszámmal foglalok, mint a szoba kapacitása");

		} catch (Exception e) {
			System.out.println("test_number_of_guests_higher_than_room_capacity_should_throw_exception");
			System.out.println("Dobott kivételt: " + e.getMessage());
			return;
		}

	}

}
