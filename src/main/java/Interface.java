
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Interface {

	static Scanner input = new Scanner(System.in);

	static Spaces spaces = new Spaces();

	public static String getUserInput() {
		return input.nextLine();
	}
	
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");

	public static void main(String[] args) {

		
		String userInput;

		// Wczytanie statusu parkingu z pliku

		try {
			FileInputStream fis = new FileInputStream("bazapojazdow.txt");
			try {
				ObjectInputStream ois = new ObjectInputStream(fis);

				try {
					List<ParkingSpace> LoadAllSpaces = (List<ParkingSpace>) ois.readObject();
					System.out.println("Rozmiar listy po za³adowaniu : " + LoadAllSpaces.size());
					spaces.addAllParkingSpace(LoadAllSpaces);
				} catch (ClassNotFoundException e) {

					System.out.println(e.getMessage());
				}
				ois.close();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());

		}

		

		do {

			System.out.println("Wybierz nr polecenia, nastêpnie zatwierdz klawiszem ENTER");
			System.out.println("[1] - dodaj pojazd do parkingu");
			System.out.println("[2] - usuñ wybrany pojazd z parkingu");
			System.out.println("[3] - poka¿ szczegó³y wybranego pojazdu");
			System.out.println("[4] - poka¿ dostêpn¹ iloœæ miejsc parkingowych");
			System.out.println("[5] - odczyt z bazy");
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
				loadJPA();
			} else if (userInput.equals("x")) {
				ExitProgram.exitProgram();

			}
		} while (!"x".equalsIgnoreCase(userInput));

		// } while (!userInput.equalsIgnoreCase("x"));

		// (!userInput.equals("x")); - zapytaæ siê czym siê ró¿ni od
		// powy¿szego --> equalsIgnoreCase ignoruje wielkoœæ liter
		

	}

	

	private static void loadJPA() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		TypedQuery<ParkingSpace> query = entityManager.createQuery("select s from ParkingSpace s", ParkingSpace.class);
		List<ParkingSpace> parkingSpacesFromJPA = query.getResultList();
		System.out.println("Iloœæ wczytanych pojazdów: " + parkingSpacesFromJPA.size());

		spaces.addAllParkingSpace(parkingSpacesFromJPA);

		entityManager.close();
	}
	
	
}
