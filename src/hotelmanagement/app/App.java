package hotelmanagement.app;

import java.time.LocalDate;
import hotelmanagement.model.*;
import hotelmanagement.model.abstractions.BaseAccommodation;
import hotelmanagement.service.Repository;

public class App {

    public static void main(String[] args) {
        try {
            System.out.println("*******************************");
            System.out.println("******** H I L T O N **********");
            System.out.println("****** B U D A P E S T ********");
            System.out.println("*******************************");

            Thread.sleep(2000);

            BaseAccommodation testAccommodation = Repository.setupTestAccommodation();

            Guest testGuest1 = Repository.getTestGuest(1);

            Guest testGuest2 = Repository.getTestGuest(2);

            simulatingAnAverageBookingProcess(testAccommodation, testGuest1);

            simulatingAnOverbookingProcess(testAccommodation, testGuest1, testGuest2);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void simulatingAnAverageBookingProcess(BaseAccommodation testAccommodation, Guest testGuest) {
        try {
            LocalDate testArrivalDate = LocalDate.of(2020, 05, 10);
            Room roomToBook = testAccommodation.getRooms().get(4);
            BookingRequest bookingRequest = new BookingRequest(testGuest, roomToBook, 2, testArrivalDate, 3);

            testGuest.book(testAccommodation, bookingRequest);
            System.out.println(testGuest.getFullName() + " sikeresen foglalt a "
                    + testAccommodation.getName() + " szálláshelyen! Dátum: " + testArrivalDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void simulatingAnOverbookingProcess(BaseAccommodation testAccommodation, Guest testGuest1, Guest testGuest2) {
        try {
            LocalDate testArrivalDate1 = LocalDate.of(2020, 05, 10);
            LocalDate testArrivalDate2 = LocalDate.of(2020, 05, 11);

            Room roomToBook = testAccommodation.getRooms().get(3);

            BookingRequest testBookingRequest1 = new BookingRequest(testGuest1, roomToBook, 2, testArrivalDate1, 2);
            BookingRequest testBookingRequest2 = new BookingRequest(testGuest2, roomToBook, 2, testArrivalDate2, 1);

            testGuest1.book(testAccommodation, testBookingRequest1);
            System.out.println(testGuest1.getFullName() + " sikeresen foglalt a "
                    + testAccommodation.getName() + " szálláshelyen! Dátum: " + testArrivalDate1);

            testGuest2.book(testAccommodation, testBookingRequest2);
            System.out.println(testGuest2.getFullName() + " sikeresen foglalt a "
                    + testAccommodation.getName() + " szálláshelyen! Dátum: " + testArrivalDate2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
