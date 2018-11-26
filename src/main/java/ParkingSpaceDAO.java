import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ParkingSpaceDAO {

				// ODCZYT Z BAZY
	
	public static void loadDatabase() {
		EntityManager entityManager = Interface.entityManagerFactory.createEntityManager();

		TypedQuery<ParkingSpace> query = entityManager.createQuery("select s from ParkingSpace s", ParkingSpace.class);
		List<ParkingSpace> parkingSpacesFromJPA = query.getResultList();
		System.out.println("Iloœæ wczytanych pojazdów: " + parkingSpacesFromJPA.size());

		Interface.spaces.addAllParkingSpace(parkingSpacesFromJPA);

		entityManager.close();
	}
	
				//	ZAPIS DO BAZY
	
	public static void saveDatabase() {
		EntityManager entityManager = Interface.entityManagerFactory.createEntityManager();		// <-------

		// otwarcie strumienia dodawania do bazy danych
		entityManager.getTransaction().begin();
		// dodanie do bazy
		ParkingSpace parkingSpace;
		for (int i = 0; i < Interface.spaces.getAllSpaces().size(); i++) {						// <-------
			parkingSpace = Interface.spaces.getAllSpaces().get(i);								// <-------
			entityManager.merge(parkingSpace);
		}
		// zamkniêcie strumienia
		entityManager.getTransaction().commit();

		entityManager.close();

		
	}

				// ODCZYT Z PLIKU
	
	public static void loadFromFile() {
		try {
			FileInputStream fis = new FileInputStream("bazapojazdow.txt");
			try {
				ObjectInputStream ois = new ObjectInputStream(fis);

				try {
					List<ParkingSpace> LoadAllSpaces = (List<ParkingSpace>) ois.readObject();
					System.out.println("Rozmiar listy po za³adowaniu : " + LoadAllSpaces.size());
					Interface.spaces.addAllParkingSpace(LoadAllSpaces);
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
	}

				// ZAPIS DO PLIKU
	
	public static void saveToFile() {
		try {
			FileOutputStream fos = new FileOutputStream("bazapojazdow.txt");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(Interface.spaces.getAllSpaces());
				oos.close();
				System.out.println("Zapisano status parkingu do pliku");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
