
public class ExitProgram {
	public static void exitProgram() {
		
	ParkingSpaceDAO parkingSpaceDAO = new ParkingSpaceDAO();	
		
		// Zapis do pliku
		
		// ParkingSpaceDAO.saveToFile();
		
		
		// Zapis do bazy

		// ParkingSpaceDAO.saveDatabase();

		parkingSpaceDAO.emfClose();
		
		System.out.println("Koniec programu");

	}

}
