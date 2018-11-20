import javax.persistence.EntityManager;

public class ExitProgram {
	public static void exitProgram() {

//		try {
//			FileOutputStream fos = new FileOutputStream("bazapojazdow.txt");
//			try {
//				ObjectOutputStream oos = new ObjectOutputStream(fos);
//				oos.writeObject(spaces.getAllSpaces());
//				oos.close();
//				System.out.println("Zapisano status parkingu do pliku");
//			} catch (IOException e) {
//				System.out.println(e.getMessage());
//			}
//
//		} catch (FileNotFoundException e) {
//			System.out.println(e.getMessage());
//		}


		
		EntityManager entityManager = Interface.entityManagerFactory.createEntityManager();

		// otwarcie strumienia dodawania do bazy danych
		entityManager.getTransaction().begin();
		// dodanie do bazy
		ParkingSpace parkingSpace;
		for (int i = 0; i < Interface.spaces.getAllSpaces().size(); i++) {
			parkingSpace = Interface.spaces.getAllSpaces().get(i);
			entityManager.merge(parkingSpace);
		}
		// zamkniêcie strumienia
		entityManager.getTransaction().commit();

		entityManager.close();

		Interface.entityManagerFactory.close();
		System.out.println("Koniec programu");
	}

}
