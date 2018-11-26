import java.util.regex.Pattern;

public class RemoveVehicle {
	public static void removeVehicle() {
		
		if (Interface.spaces.getOccupiedSpaces() == 0) {   									 		// <-------
			System.out.println("Brak pojazd�w na parkingu" + "\n");
		} else {
			ParkingSpace parkingSpace;

			for (int i = 0; i < Interface.spaces.getAllSpaces().size(); i++) {   			 		// <-------
				parkingSpace = Interface.spaces.getAllSpaces().get(i);   					 		// <-------
				System.out.println(i + 1 + ": " + parkingSpace.getRegistrationNumber());
			}

			System.out.println();
			Pattern numberPattern = Pattern.compile("[0-9]+");
			String spaceNumber;

			System.out.println("Podaj nr pojazdu do usuni�cia");
			spaceNumber = Interface.getUserInput();
			if (numberPattern.matcher(spaceNumber).matches()) {
				// jest -1 aby numeracja zaczynala sie od 1
				int carNumber = Integer.parseInt(spaceNumber) - 1;

				if (Interface.spaces.getAllSpaces().size() > carNumber && carNumber >= 0) {   		 // <-------
					// TODO gdy uzytkownik poda carNumber=0 to wywala blad ArrayIndexOutOfBounds,
					// tzn. ze nie da sie usunac z listy pozycji "-1". Wy�ej doda�em warunek
					// && carNumber >= 0 i dzia�a. Czy nie lepiej zaimplemenotwa� try catch
					// tego wyj�tku?
					Interface.spaces.getAllSpaces().remove(carNumber);  // <-------

					System.out.println("Usuni�to pojazd z parkingu");
				} else {
					System.out.println("Brak podanego pojazdu na parkingu");
				}

			} else {
				System.out.println("Niepoprawny numer pojazdu." + "\n");
			}
		}

	}
}
