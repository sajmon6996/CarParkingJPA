import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ParkingSpaceDatabaseDAO implements ParkingSpaceDAO {

	private final EntityManagerFactory entityManagerFactory;	
	
	public ParkingSpaceDatabaseDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	// ODCZYT Z BAZY

	public List<ParkingSpace> loadAll() {

		List<ParkingSpace> loadFromDatabase = null;

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		TypedQuery<ParkingSpace> query = entityManager.createQuery("select s from ParkingSpace s", ParkingSpace.class);
		loadFromDatabase = query.getResultList();
		System.out.println("Iloœæ wczytanych pojazdów: " + loadFromDatabase.size());

		entityManager.close();

		return loadFromDatabase;
	}

	// ZAPIS DO BAZY

	public void saveAll(List<ParkingSpace> saveSpaces) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// otwarcie strumienia dodawania do bazy danych
		entityManager.getTransaction().begin();

		// usuwa istniej¹ce rekordy z bazy
		Query q1 = entityManager.createQuery("DELETE FROM ParkingSpace");
		q1.executeUpdate();

		// dodanie do bazy
		ParkingSpace parkingSpace;
		for (int i = 0; i < saveSpaces.size(); i++) {
			parkingSpace = saveSpaces.get(i);
			entityManager.merge(parkingSpace); // TODO <------ persist nie dzia³a. b³¹d: detached entity passed to
												// persist:
			// ParkingSpace
		}

		// zamkniêcie strumienia
		entityManager.getTransaction().commit();

		entityManager.close();

	}

}
