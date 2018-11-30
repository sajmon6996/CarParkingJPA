import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ParkingSpaceDAO {

	// ODCZYT Z BAZY
	private EntityManagerFactory emf;
	private EntityManager em;

	public EntityManagerFactory getEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("myDatabase");
		return emf;
	}

	public EntityManager getEntityManager() {
		em = emf.createEntityManager();
		return em;
	}

	public void emfClose() {
		emf.close();
	}

	public void loadDatabase() {
		
		em = emf.createEntityManager();
		
		TypedQuery<ParkingSpace> query = em.createQuery("select s from ParkingSpace s", ParkingSpace.class);
		List<ParkingSpace> parkingSpacesFromJPA = query.getResultList();
		System.out.println("Iloœæ wczytanych pojazdów: " + parkingSpacesFromJPA.size());

		Interface.spaces.addAllParkingSpace(parkingSpacesFromJPA);

		em.close();
	}

	// ZAPIS DO BAZY

	public void saveDatabase() {
		
		em = emf.createEntityManager();
		
		// otwarcie strumienia dodawania do bazy danych
		em.getTransaction().begin();

		// usuwa istniej¹ce rekordy z bazy
		Query q1 = em.createQuery("DELETE FROM ParkingSpace");
		q1.executeUpdate();

		// dodanie do bazy
		ParkingSpace parkingSpace;
		for (int i = 0; i < Interface.spaces.getAllSpaces().size(); i++) { // <-------
			parkingSpace = Interface.spaces.getAllSpaces().get(i); // <-------
			em.merge(parkingSpace);
		}
		
		// zamkniêcie strumienia
		em.getTransaction().commit();

		em.close();

	}

	// ODCZYT Z PLIKU

	public void loadFromFile() {
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

	public void saveToFile() {
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
