package one.digitalinnovation.cloudparking.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.cloudparking.controller.dto.ParkingDTO;
import one.digitalinnovation.cloudparking.controller.mapper.ParkingMapper;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;

	//Construtor que substitu o autowide
    ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
    }
	
	
	
	@GetMapping
	public List<ParkingDTO> findAll(){
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return result;
	}

}
