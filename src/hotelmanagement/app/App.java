package hotelmanagement.app;

import hotelmanagement.service.Accommodation;
import hotelmanagement.service.BaseAccommodation;

public class App {

	private static BaseAccommodation testAccommodation;

	public static void main(String[] args) {
		try {
			testAccommodation = Accommodation.getAccommodation();

			System.out.println("*******************************");
			System.out.println("******* V I R T U A L *********");
			System.out.println("*** R E C E P T I O N I S T ***");
			System.out.println("*******************************");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
}
