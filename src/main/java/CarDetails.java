import java.util.regex.Pattern;

public class CarDetails {
	public static void carDetails() {
		if (Interface.spaces.getOccupiedSpaces() == 0) {    // <-------
			System.out.println("Brak pojazdów na parkingu" + "\n");
		} else {
			ParkingSpace parkingSpace;

			for (int i = 0; i < Interface.spaces.getAllSpaces().size(); i++) {    // <-------
				parkingSpace = Interface.spaces.getAllSpaces().get(i);    // <-------
				// i + 1 aby numeracja zaczyna³a siê od 1 a nie od 0
				System.out.println(i + 1 + ": " + parkingSpace.getRegistrationNumber());
			}

			System.out.println();
			Pattern numberPattern = Pattern.compile("[0-9]+");
			String spaceNumber;

			System.out.println("Podaj nr pojazdu");
			spaceNumber = Interface.getUserInput();    // <-------

			if (numberPattern.matcher(spaceNumber).matches()) {
				// jest -1 aby numeracja zaczynala sie od 1
				int carNumber = Integer.parseInt(spaceNumber) - 1;

				if (Interface.spaces.getAllSpaces().size() > carNumber && carNumber >= 0) {    // <-------
					// TODO gdy uzytkownik poda carNumber=0 to wywala blad ArrayIndexOutOfBounds,
					// tzn. ze nie da sie usunac z listy pozycji "-1". Wy¿ej doda³em warunek
					// && carNumber >= 0 i dzia³a. Czy nie lepiej zaimplemenotwaæ try catch
					// tego wyj¹tku?
					ParkingSpace carChosen = Interface.spaces.getAllSpaces().get(carNumber);    // <-------
					System.out.println(carChosen.getOwnerName() + " " + carChosen.getOwnerSurname());
					System.out.println(carChosen.getCarBrand() + " " + carChosen.getCarModel());
					System.out.println(carChosen.getRegistrationNumber());
				} else {
					System.out.println("Brak podanego pojazdu na parkingu");
				}
			} else {
				System.out.println("Niepoprawny numer pojazdu." + "\n");
			}
		}
	}

}
