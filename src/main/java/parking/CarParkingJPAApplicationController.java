package parking;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class CarParkingJPAApplicationController {
	@RequestMapping("/lol")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	@RequestMapping("/")
	public String index1() {
		return "Main page";
	}
}
