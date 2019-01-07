package parking;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarParkingJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarParkingJpaApplication.class, args);
		
		Scanner input = new Scanner(System.in);

		Spaces spaces = new Spaces();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		
		ParkingSpaceDAO parkingSpaceDatabaseDAO = new ParkingSpaceDatabaseDAO(entityManagerFactory);
		ParkingSpaceDAO parkingSpaceFileDAO = new ParkingSpaceFileDAO();

		// TODO dodaæ autoodczyt bazy/pliku

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

			userInput = input.nextLine();

			if (userInput.equals("1")) {
				int occupiedSpaces = spaces.getOccupiedSpaces();
				if (occupiedSpaces >= spaces.getParkingCapacity()) {
					System.out.println("===========================");
					System.out.println("=== Brak wolnych miejsc ===");
					System.out.println("===========================");

				} else {
					addVehicle(spaces, input);
				}
			} else if (userInput.equals("2")) {
				removeVehicle(spaces, input);
			} else if (userInput.equals("3")) {
				carDetails(spaces, input);
			} else if (userInput.equals("4")) {
				parkingStatus(spaces);
			} else if (userInput.equals("5")) {
				spaces.addAllParkingSpace(parkingSpaceFileDAO.loadAll());
			} else if (userInput.equals("6")) {
				parkingSpaceFileDAO.saveAll(spaces.getAllSpaces());
			} else if (userInput.equals("7")) {
				spaces.addAllParkingSpace(parkingSpaceDatabaseDAO.loadAll());
			} else if (userInput.equals("8")) {
				parkingSpaceDatabaseDAO.saveAll(spaces.getAllSpaces());
			} else if (userInput.equals("x")) {
				exitProgram(entityManagerFactory);

			}
		} while (!"x".equalsIgnoreCase(userInput));

	}

	public static void addVehicle(Spaces spaces, Scanner input) {

		ParkingSpace parkingSpaceInput = new ParkingSpace();

		do {
			System.out.println("Podaj imiê");
			parkingSpaceInput.setOwnerName(input.nextLine());
		} while (parkingSpaceInput.getOwnerName().length() == 0);

		do {
			System.out.println("Podaj nazwisko");
			parkingSpaceInput.setOwnerSurname(input.nextLine());
		} while (parkingSpaceInput.getOwnerSurname().length() == 0);

		do {
			System.out.println("Podaj markê samochodu");
			parkingSpaceInput.setCarBrand(input.nextLine());
		} while (parkingSpaceInput.getCarBrand().length() == 0);

		do {
			System.out.println("Podaj model samochodu");
			parkingSpaceInput.setCarModel(input.nextLine());
		} while (parkingSpaceInput.getCarModel().length() == 0);

		do {
			System.out.println("Podaj nr rejestarcyjny pojazdu");
			parkingSpaceInput.setRegistrationNumber(input.nextLine());
		} while (parkingSpaceInput.getRegistrationNumber().length() == 0);

		spaces.addParkingSpace(parkingSpaceInput);

		System.out.println("Dodano pojazd do parkingu.");

	}

	public static void removeVehicle(Spaces spaces, Scanner input) {

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
			spaceNumber = input.nextLine();
			if (numberPattern.matcher(spaceNumber).matches()) {

				int carNumber = Integer.parseInt(spaceNumber) - 1;
				// jest -1 aby numeracja zaczynala sie od 1

				if (spaces.getAllSpaces().size() > carNumber && carNumber >= 0) {
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

	public static void carDetails(Spaces spaces, Scanner input) {
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
			spaceNumber = input.nextLine();

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

	public static void parkingStatus(Spaces spaces) {

		System.out.println("Miesjca zajête: " + spaces.getOccupiedSpaces() + "\n");
		System.out.println("Miejsca wolne: " + spaces.getFreeSpaces() + "\n");

	}

	public static void exitProgram(EntityManagerFactory entityManagerFactory) {

//TODO dodaæ autozapis bazy/pliku

		entityManagerFactory.close();
		System.out.println("Koniec programu");

	}

}
