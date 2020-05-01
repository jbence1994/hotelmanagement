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
			testAccommodation = new Accommodation();
			input = new Scanner(System.in);

			System.out.println("*******************************");
			System.out.println("******* V I R T U A L *********");
			System.out.println("*** R E C E P T I O N I S T ***");
			System.out.println("*******************************");

			System.out.println("Kérem válasszon menüpontot:\n");
			System.out.println("1) Foglalás rögzítése\n2) Foglalás törlése\n3) Foglalások listázása");

			System.out.print("Adja meg a menüpont számát: ");
			int menu = input.nextInt();

			switch (menu) {
			case 1:
				addBooking();
				break;
			case 2:
				deleteBooking();
				break;
			case 3:
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

		int id = input.nextInt();

		// Booking newBooking = new Booking();

		// testAccommodation.addBooking();

		System.out.println(id + " azonosítójú foglalás sikeresen rögzítésre került!");

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
		System.out.println("Foglalások listája: ");
		for (Booking booking : testAccommodation.getBookings()) {
			System.out.println(booking);
		}
	}

}
