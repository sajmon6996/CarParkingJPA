import java.util.List;

public interface ParkingSpaceDAO {

	List<ParkingSpace> loadAll();
	
	void saveAll(List<ParkingSpace> saveSpaces);
}
