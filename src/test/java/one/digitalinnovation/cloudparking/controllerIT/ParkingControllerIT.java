package one.digitalinnovation.cloudparking.controllerIT;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class ParkingControllerIT {
	
	@LocalServerPort
	private int randomPort; 	//criando porta aleatoria
	
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
			.body("license[0]", Matchers.equalTo("DMS-1111"));
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
