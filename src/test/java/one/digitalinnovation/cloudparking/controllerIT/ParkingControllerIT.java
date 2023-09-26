package one.digitalinnovation.cloudparking.controllerIT;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.model.Parking;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class ParkingControllerIT {
	
	@LocalServerPort
	private int randomPort; 	//criando porta aleatoria
	
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	//List de dados mokados
	private static Map<String, Parking> parkingMap = new HashMap<>();
	
	static {
		var id1 = getUUID();
		var id2 = getUUID();
		Parking parking = new Parking(id1, "DMS-1111", "SC", "CELTA", "Preto");
		Parking parking2 = new Parking(id2, "DMS-2222", "RJ", "CELTA", "Preto");
		parkingMap.put(id1, parking);
		parkingMap.put(id2, parking2);

	}
	
	@BeforeEach
	public void setUpteste() {
		RestAssured.port = randomPort;
	}
	
	@Test
	void whenFindAllThenCheckResult() {
		RestAssured.given()
			.when()
			.get("/parking")
			.then()
			.statusCode(200)
			.body("license[1]", Matchers.equalTo("DMS-1111"));
	}
	
	@Test
	void whenCreateThenCheckIsCreated() {
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("Green");
		createDTO.setLicense("GGD-3333");
		createDTO.setModel("Uno");
		createDTO.setState("MG");
		
		RestAssured.given()
			.when()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(createDTO)
			.post("/parking")
			.then()
			.statusCode(201)
			.body("license", Matchers.equalTo("GGD-3333"));
	}

}
