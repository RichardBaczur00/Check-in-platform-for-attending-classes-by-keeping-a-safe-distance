package uvt.projibm.checkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class CheckinApplication {
	public static void main(String[] args) {
		SpringApplication.run(CheckinApplication.class, args);
	}
}
