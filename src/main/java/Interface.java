import java.util.Scanner;
import java.util.regex.Pattern;

public class Interface {

	static Scanner input = new Scanner(System.in);

	static Spaces spaces = new Spaces();

	static ParkingSpaceDAO1 parkingSpaceDAO = new ParkingSpaceDAO1();

	public static String getUserInput() {
		return input.nextLine();
	}

	public static void main(String[] args) {

		// Wczytanie statusu parkingu z pliku

		// parkingSpaceDAO.loadFromFile();

		// Wczytanie z bazy

		// parkingSpaceDAO.loadDatabase();

		parkingSpaceDAO.getEntityManagerFactory();

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
					addVehicle();
				}
			} else if (userInput.equals("2")) {
				removeVehicle();
			} else if (userInput.equals("3")) {
				carDetails();
			} else if (userInput.equals("4")) {
				parkingStatus();
			} else if (userInput.equals("5")) {
				spaces.addAllParkingSpace(parkingSpaceDAO.loadFromFile());
			} else if (userInput.equals("6")) {
				parkingSpaceDAO.saveToFile(spaces.getAllSpaces());
			} else if (userInput.equals("7")) {
				spaces.addAllParkingSpace(parkingSpaceDAO.loadDatabase());
			} else if (userInput.equals("8")) {
				parkingSpaceDAO.saveDatabase(spaces.getAllSpaces());
			} else if (userInput.equals("x")) {
				exitProgram();

			}
		} while (!"x".equalsIgnoreCase(userInput));

		// } while (!userInput.equalsIgnoreCase("x"));

		// (!userInput.equals("x")); - zapytaæ siê czym siê ró¿ni od
		// powy¿szego --> equalsIgnoreCase ignoruje wielkoœæ liter

	}

	public static void addVehicle() {

		ParkingSpace parkingSpaceInput = new ParkingSpace();

		do {
			System.out.println("Podaj imiê");
			parkingSpaceInput.setOwnerName(getUserInput());
		} while (parkingSpaceInput.getOwnerName().length() == 0);

		do {
			System.out.println("Podaj nazwisko");
			parkingSpaceInput.setOwnerSurname(getUserInput());
		} while (parkingSpaceInput.getOwnerSurname().length() == 0);

		do {
			System.out.println("Podaj markê samochodu");
			parkingSpaceInput.setCarBrand(getUserInput());
		} while (parkingSpaceInput.getCarBrand().length() == 0);

		do {
			System.out.println("Podaj model samochodu");
			parkingSpaceInput.setCarModel(getUserInput());
		} while (parkingSpaceInput.getCarModel().length() == 0);

		do {
			System.out.println("Podaj nr rejestarcyjny pojazdu");
			parkingSpaceInput.setRegistrationNumber(getUserInput());
		} while (parkingSpaceInput.getRegistrationNumber().length() == 0);

		spaces.addParkingSpace(parkingSpaceInput);

		System.out.println("Dodano pojazd do parkingu.");

	}

	public static void removeVehicle() {

		if (spaces.getOccupiedSpaces() == 0) {
			System.out.println("Brak pojazdów na parkingu" + "\n");
		} else {
			ParkingSpace parkingSpace;

			for (int i = 0; i < spaces.getAllSpaces().size(); i++) {
				parkingSpace = spaces.getAllSpaces().get(i);
				System.out.println(i + 1 + ": " + parkingSpace.getRegistrationNumber());
			}

			System.out.println();
			Pattern numberPattern = Pattern.compile("[0-9]+");
			String spaceNumber;

			System.out.println("Podaj nr pojazdu do usuniêcia");
			spaceNumber = Interface.getUserInput();
			if (numberPattern.matcher(spaceNumber).matches()) {

				int carNumber = Integer.parseInt(spaceNumber) - 1;
				// jest -1 aby numeracja zaczynala sie od 1

				if (spaces.getAllSpaces().size() > carNumber && carNumber >= 0) {
					// TODO gdy uzytkownik poda carNumber=0 to wywala blad ArrayIndexOutOfBounds,
					// tzn. ze nie da sie usunac z listy pozycji "-1". Wy¿ej doda³em warunek
					// && carNumber >= 0 i dzia³a. Czy nie lepiej zaimplemenotwaæ try catch
					// tego wyj¹tku?
					spaces.getAllSpaces().remove(carNumber);

					System.out.println("Usuniêto pojazd z parkingu");
				} else {
					System.out.println("Brak podanego pojazdu na parkingu");
				}

			} else {
				System.out.println("Niepoprawny numer pojazdu." + "\n");
			}
		}

	}

	public static void carDetails() {
		if (spaces.getOccupiedSpaces() == 0) {
			System.out.println("Brak pojazdów na parkingu" + "\n");
		} else {
			ParkingSpace parkingSpace;

			for (int i = 0; i < spaces.getAllSpaces().size(); i++) {
				parkingSpace = spaces.getAllSpaces().get(i);

				System.out.println(i + 1 + ": " + parkingSpace.getRegistrationNumber());
				// i + 1 aby numeracja zaczyna³a siê od 1 a nie od 0
			}

			System.out.println();
			Pattern numberPattern = Pattern.compile("[0-9]+");
			String spaceNumber;

			System.out.println("Podaj nr pojazdu");
			spaceNumber = getUserInput();

			if (numberPattern.matcher(spaceNumber).matches()) {

				int carNumber = Integer.parseInt(spaceNumber) - 1;
				// jest -1 aby numeracja zaczynala sie od 1

				if (spaces.getAllSpaces().size() > carNumber && carNumber >= 0) {
					ParkingSpace carChosen = spaces.getAllSpaces().get(carNumber);
					System.out.println(carChosen.getOwnerName() + " " + carChosen.getOwnerSurname());
					System.out.println(carChosen.getCarBrand() + " " + carChosen.getCarModel());
					System.out.println(carChosen.getRegistrationNumber());
				} else {
					System.out.println("Brak podanego pojazdu na parkingu");
				}
			} else {
				System.out.println("Niepoprawny numer pojazdu." + "\n");
			}
		}
	}

	public static void parkingStatus() {

		System.out.println("Miesjca zajête: " + spaces.getOccupiedSpaces() + "\n");
		System.out.println("Miejsca wolne: " + spaces.getFreeSpaces() + "\n");

	}

	public static void exitProgram() {

		// Zapis do pliku

		// ParkingSpaceDAO.saveToFile();

		// Zapis do bazy

		// ParkingSpaceDAO.saveDatabase();

		parkingSpaceDAO.emfClose();

		System.out.println("Koniec programu");

	}

}
