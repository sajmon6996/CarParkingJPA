import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Interface {

	static Scanner input = new Scanner(System.in);

	static Spaces spaces = new Spaces();

	public static String getUserInput() {
		return input.nextLine();
	}

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");

	public static void main(String[] args) {

		// Wczytanie statusu parkingu z pliku

		// ParkingSpaceDAO.loadFromFile();

		// Wczytanie z bazy

		// ParkingSpaceDAO.loadDatabase();

		String userInput;

		do {

			System.out.println("Wybierz nr polecenia, nastêpnie zatwierdz klawiszem ENTER");
			System.out.println("[1] - dodaj pojazd do parkingu");
			System.out.println("[2] - usuñ wybrany pojazd z parkingu");
			System.out.println("[3] - poka¿ szczegó³y wybranego pojazdu");
			System.out.println("[4] - poka¿ dostêpn¹ iloœæ miejsc parkingowych");
			System.out.println("[5] - odczyt z pliku");
			System.out.println("[6] - Zapis do pliku");
			System.out.println("[7] - odczyt z bazy");
			System.out.println("[8] - zapis do bazy");
			System.out.println("[x] - zakoñcz program");

			userInput = getUserInput();

			if (userInput.equals("1")) {
				int occupiedSpaces = spaces.getOccupiedSpaces();
				if (occupiedSpaces >= spaces.getParkingCapacity()) {
					System.out.println("===========================");
					System.out.println("=== Brak wolnych miejsc ===");
					System.out.println("===========================");

				} else {
					AddVehicle.addVehicle();
				}
			} else if (userInput.equals("2")) {
				RemoveVehicle.removeVehicle();
			} else if (userInput.equals("3")) {
				CarDetails.carDetails();
			} else if (userInput.equals("4")) {
				ParkingStatus.parkingStatus();
			} else if (userInput.equals("5")) {
				ParkingSpaceDAO.loadFromFile();
			} else if (userInput.equals("6")) {
				ParkingSpaceDAO.saveToFile();
			} else if (userInput.equals("7")) {
				ParkingSpaceDAO.loadDatabase();
			} else if (userInput.equals("8")) {
				ParkingSpaceDAO.saveDatabase();
			} else if (userInput.equals("x")) {
				ExitProgram.exitProgram();

			}
		} while (!"x".equalsIgnoreCase(userInput));

		// } while (!userInput.equalsIgnoreCase("x"));

		// (!userInput.equals("x")); - zapytaæ siê czym siê ró¿ni od
		// powy¿szego --> equalsIgnoreCase ignoruje wielkoœæ liter

	}

}
