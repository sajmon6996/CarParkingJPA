import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ParkingSpaceFileDAO implements ParkingSpaceDAO {

	public List<ParkingSpace> loadAll() {

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

	public void saveAll(List<ParkingSpace> saveSpaces) {
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
