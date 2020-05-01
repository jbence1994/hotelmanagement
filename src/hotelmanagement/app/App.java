package hotelmanagement.app;

import java.util.Scanner;

import hotelmanagement.model.Booking;
import hotelmanagement.service.Accommodation;
import hotelmanagement.service.BaseAccommodation;

public class App {

	private static BaseAccommodation testAccommodation;
	private static Scanner input;
	private static final String exitMessage = "Érvénytelen menüpont... Az alkalmazás leáll...";

	public static void main(String[] args) {
		try {
			testAccommodation = Accommodation.getAccommodation();
			input = new Scanner(System.in);

			System.out.println("*******************************");
			System.out.println("******* V I R T U A L *********");
			System.out.println("*** R E C E P T I O N I S T ***");
			System.out.println("*******************************");
			Thread.sleep(2000);

			System.out.println("Kérem válasszon menüpontot:\n");
			System.out.println(
					"1) Foglalás rögzítése\n2) Foglalás módosítása" + "\n3) Foglalás törlése\n4) Foglalások listázása");

			System.out.print("Adja meg a menüpont számát: ");
			int menu = input.nextInt();

			switch (menu) {
			case 1:
				addBooking();
				break;
			case 2:
				updateBooking();
				break;
			case 3:
				deleteBooking();
				break;
			case 4:
				showBookings();
				break;
			default:
				System.out.println(exitMessage);
				break;
			}
		} catch (Exception e) {
			System.out.println(exitMessage);
		}
	}

	private static void addBooking() {
		System.out.println("Foglalás rögzítése: ");
		System.out.println("A vendég törzsvendég?");

		int id = input.nextInt();

		// testAccommodation.addBooking();

		System.out.println(id + " azonosítójú foglalás sikeresen rögzítésre került!");

	}

	private static void updateBooking() {
		try {
			System.out.println("Kérem adja meg a törlendő foglalás azonosítóját!");
			int id = input.nextInt();
			Booking booking = testAccommodation.getBookingById(id);
			testAccommodation.updateBooking(booking);
			System.out.println(id + " azonosítójú foglalás sikeresen módosult!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void deleteBooking() {
		try {
			System.out.println("Kérem adja meg a törlendő foglalás azonosítóját!");
			int id = input.nextInt();
			testAccommodation.deleteBooking(id);
			System.out.println(id + " azonosítójú foglalás sikeresen törlésre került!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void showBookings() {
		System.out.println("Aktív foglalások: ");
		for (Booking booking : testAccommodation.getBookings()) {
			System.out.println(booking);
		}
	}

}
