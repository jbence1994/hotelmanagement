package hotelmanagement.app;

import java.time.LocalDate;
import java.util.*;
import hotelmanagement.model.*;
import hotelmanagement.service.*;

public class App {

	private static BaseAccommodation testAccommodation;
	private static final String exitMessage = "Érvénytelen menüpont. Az alkalmazás leáll...";

	public static void main(String[] args) {

		try {
			Scanner input = new Scanner(System.in);
			testAccommodation = new Accommodation();

			System.out.println("*******************************");
			System.out.println("******* V I R T U A L *********");
			System.out.println("*** R E C E P T I O N I S T ***");
			System.out.println("*******************************");

			while (true) {
				System.out.println("Kérem válasszon menüpontot:\n");
				System.out.println(
						"1) Foglalás rögzítése\n2) Foglalás törlése\n3) Foglalások listázása\n4) Kilépés az alkalmazásból");

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
				case 4:
					System.exit(0);
					break;
				default:
					System.out.println(exitMessage);
					break;
				}
			}

		} catch (Exception e) {
			System.out.println(exitMessage);
			return;
		}
	}

	private static void addBooking() {
		Scanner input = new Scanner(System.in);

		try {
			System.out.println("Foglalás rögzítése...");

			System.out.println("Adja meg a vendég keresztnevét!");
			String firstName = input.nextLine();
			System.out.println("Adja meg a vendég vezetéknevét!");
			String lastName = input.nextLine();
			System.out.println("Adja meg a vendég állampolgárságát!");
			String citizenship = input.nextLine();

			Guest guest = new Guest(firstName, lastName, citizenship);

			System.out.println("Adja meg a szobaszámot!");
			int roomNumber = input.nextInt();

			Room room = testAccommodation.getRoom(roomNumber);

			System.out.println("Adja meg a vendégek számát!");
			int numberOfGuests = input.nextInt();

			System.out.println("Adja meg az érkezés dátumának évszámát!");
			int year = input.nextInt();

			System.out.println("Adja meg az érkezés dátumának hónapját!");
			int month = input.nextInt();

			System.out.println("Adja meg az érkezés dátumának napját!");
			int day = input.nextInt();

			System.out.println("Adja meg az éjszakák számát!");
			int numberOfNights = input.nextInt();

			LocalDate arrivalDate = LocalDate.of(year, month, day);

			Booking newBooking = new Booking(guest, room, numberOfGuests, arrivalDate, numberOfNights);
			testAccommodation.addBooking(newBooking);

			System.out.println("A foglalás sikeresen rögzítésre került!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void deleteBooking() {
		Scanner input = new Scanner(System.in);
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
