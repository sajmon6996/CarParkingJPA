
public class ExitProgram {
	public static void exitProgram() {
		
		// Zapis do pliku
		
		// ParkingSpaceDAO.saveToFile();
		
		
		// Zapis do bazy

		// ParkingSpaceDAO.saveDatabase();

		Interface.entityManagerFactory.close();			// <-------
		
		System.out.println("Koniec programu");

	}

}
