import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ParkingSpaceDatabaseDAO implements ParkingSpaceDAO {
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

	public List<ParkingSpace> loadAll() {

		List<ParkingSpace> loadFromDatabase = null;

		em = emf.createEntityManager();

		TypedQuery<ParkingSpace> query = em.createQuery("select s from ParkingSpace s", ParkingSpace.class);
		loadFromDatabase = query.getResultList();
		System.out.println("Iloœæ wczytanych pojazdów: " + loadFromDatabase.size());

		em.close();

		return loadFromDatabase;
	}

	// ZAPIS DO BAZY

	public void saveAll(List<ParkingSpace> saveSpaces) {

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

}
