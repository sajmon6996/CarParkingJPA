import java.util.ArrayList;
import java.util.List;

public class Spaces {

	private List<ParkingSpace> allSpaces = new ArrayList<ParkingSpace>();
	
	public int getParkingCapacity() {
		return 10;
	}

	public List<ParkingSpace> getAllSpaces() {
		return allSpaces;
	}

	public void addParkingSpace(ParkingSpace parkingSpace) {
		allSpaces.add(parkingSpace);
		System.out.println("Dodano pojazd do parkingu.");
	}

	public void removeParkingSpace(ParkingSpace parkingSpace) {
		allSpaces.remove(parkingSpace);
		System.out.println("Usuniêto pojazd do parkingu.");
	}

	public int getOccupiedSpaces() {
		return allSpaces.size();
	}

	public int getFreeSpaces() {
		return getParkingCapacity() - getOccupiedSpaces();
	}
	
	public void addAllParkingSpace(List<ParkingSpace> spaces) {
		   allSpaces.addAll(spaces);
		}

}