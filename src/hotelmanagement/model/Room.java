package hotelmanagement.model;

public class Room {

	private int number;
	private String name;
	private int capacity;
	private double price;
	private boolean reserved;

	public Room(int number, String name, int capacity, double price) {
		this.number = number;
		this.name = name;
		this.capacity = capacity;
		this.price = price;
		reserved = false;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	@Override
	public String toString() {
		return "A szoba száma: " + number + ", neve: " + name + ", maximális férőhelye: " + capacity + " fő. Ára: "
				+ price + " Forint." + " A szoba jelenleg " + (reserved ? "foglalt" : "szabad") + ".";
	}

}
