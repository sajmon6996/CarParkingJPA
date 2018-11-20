
public class ParkingStatus {
	public static void parkingStatus() {

		System.out.println("Miesjca zajête: " + Interface.spaces.getOccupiedSpaces() + "\n");
		System.out.println("Miejsca wolne: " + Interface.spaces.getFreeSpaces() + "\n");

	}
}
