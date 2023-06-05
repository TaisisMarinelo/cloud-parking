package one.digitalinnovation.parking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.parking.controller.DTO.ParkingDTO;
import one.digitalinnovation.parking.controller.mapper.ParkingMapper;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	//Injeção de dependencia sem o autowide, realizada via construtor
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService =  parkingService;
		this.parkingMapper =  parkingMapper; 
	}
	
	
	@GetMapping
	public List<ParkingDTO> findAll(){
		List<Parking> parkingList = parkingService.findAll(); 
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return result;
		
		//return parkingService.findAll();	//antes do DTO
		
		/*var parking = new Parking();
		parking.setColor("Preto");
		parking.setLicense("MSS-1111");
		parking.setModel("VW GOL");
		parking.setState("SP");
		return Arrays.asList(parking);*/
	}

}
