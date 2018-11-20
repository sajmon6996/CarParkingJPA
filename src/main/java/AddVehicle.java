
public class AddVehicle {
	
	public static void addVehicle() {

		ParkingSpace parkingSpaceInput = new ParkingSpace();

		do {
			System.out.println("Podaj imiê");
			parkingSpaceInput.setOwnerName(Interface.getUserInput()); // <----- stworzyc getUserInput czy odwolywac sie do Interface?
		} while (parkingSpaceInput.getOwnerName().length() == 0);

		do {
			System.out.println("Podaj nazwisko");
			parkingSpaceInput.setOwnerSurname(Interface.getUserInput());    // <-------
		} while (parkingSpaceInput.getOwnerSurname().length() == 0);

		do {
			System.out.println("Podaj markê samochodu");
			parkingSpaceInput.setCarBrand(Interface.getUserInput());    // <-------
		} while (parkingSpaceInput.getCarBrand().length() == 0);

		do {
			System.out.println("Podaj model samochodu");
			parkingSpaceInput.setCarModel(Interface.getUserInput());    // <-------
		} while (parkingSpaceInput.getCarModel().length() == 0);

		do {
			System.out.println("Podaj nr rejestarcyjny pojazdu");
			parkingSpaceInput.setRegistrationNumber(Interface.getUserInput());    // <-------
		} while (parkingSpaceInput.getRegistrationNumber().length() == 0);

		Interface.spaces.addParkingSpace(parkingSpaceInput); // <----- stworzyc spaces czy odwolywac sie do Interface?

		// System.out.println(parkingSpaceInput.getSpaceStatus());
		System.out.println("Dodano pojazd do parkingu.");

	}
}
