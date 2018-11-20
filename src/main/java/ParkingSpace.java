import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ParkingSpace implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//automatycznie generuje ID
	@GeneratedValue
	private long id;
	
	private String ownerName;
	private String ownerSurname;
	private String carBrand;
	private String carModel;
	private String registrationNumber;

	/*
	 * public String getSpaceStatus() { return "Miejsce zajête przez: " + ownerName
	 * + " " + ownerSurname + "; " + carBrand + " " + carModel + "; " +
	 * registrationNumber + "."; }
	 */

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerSurname() {
		return ownerSurname;
	}

	public void setOwnerSurname(String ownerSurname) {
		this.ownerSurname = ownerSurname;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

}
