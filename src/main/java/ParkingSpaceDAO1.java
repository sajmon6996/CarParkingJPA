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

public class ParkingSpaceDAO1 {


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
	
	// ODCZYT Z BAZY

	public List<ParkingSpace> loadDatabase() {
		
		List<ParkingSpace> loadFromDatabase = null;

		em = emf.createEntityManager();

		TypedQuery<ParkingSpace> query = em.createQuery("select s from ParkingSpace s", ParkingSpace.class);
		loadFromDatabase = query.getResultList();
		System.out.println("Iloœæ wczytanych pojazdów: " + loadFromDatabase.size());

		em.close();
		
		return loadFromDatabase;
	}
	

	// ZAPIS DO BAZY

	public void saveDatabase(List<ParkingSpace> saveSpaces) {

		em = emf.createEntityManager();

		// otwarcie strumienia dodawania do bazy danych
		em.getTransaction().begin();

		// usuwa istniej¹ce rekordy z bazy
		Query q1 = em.createQuery("DELETE FROM ParkingSpace");
		q1.executeUpdate();

		// dodanie do bazy
		ParkingSpace parkingSpace;
		for (int i = 0; i < saveSpaces.size(); i++) {
			parkingSpace = saveSpaces.get(i);
			em.merge(parkingSpace); // TODO <------ persist nie dzia³a. b³¹d: detached entity passed to persist:
									// ParkingSpace
		}

		// zamkniêcie strumienia
		em.getTransaction().commit();

		em.close();

	}

	// ODCZYT Z PLIKU

	public List<ParkingSpace> loadFromFile() {

		List<ParkingSpace> LoadAllSpaces = null;

		try {
			FileInputStream fis = new FileInputStream("bazapojazdow.txt");
			try {
				ObjectInputStream ois = new ObjectInputStream(fis);

				try {
					LoadAllSpaces = (List<ParkingSpace>) ois.readObject();
					System.out.println("Rozmiar listy po za³adowaniu : " + LoadAllSpaces.size());
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

		return LoadAllSpaces;
	}

	// ZAPIS DO PLIKU

	public void saveToFile(List<ParkingSpace> saveSpaces) {
		try {
			FileOutputStream fos = new FileOutputStream("bazapojazdow.txt");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(saveSpaces);
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
