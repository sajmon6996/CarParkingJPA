import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ParkingSpaceFileDAO implements ParkingSpaceDAO {

	public List<ParkingSpace> loadAll() {

		List<ParkingSpace> loadAllSpaces = null;

		try {
			FileInputStream fileInputStream = new FileInputStream("bazapojazdow.txt");
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

				try {
					loadAllSpaces = (List<ParkingSpace>) objectInputStream.readObject();
					System.out.println("Rozmiar listy po za³adowaniu : " + loadAllSpaces.size());
				} catch (ClassNotFoundException e) {

					System.out.println(e.getMessage());
				}
				objectInputStream.close();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		return loadAllSpaces;
	}

	// ZAPIS DO PLIKU

	public void saveAll(List<ParkingSpace> saveSpaces) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("bazapojazdow.txt");
			try {
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(saveSpaces);
				objectOutputStream.close();
				System.out.println("Zapisano status parkingu do pliku");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
