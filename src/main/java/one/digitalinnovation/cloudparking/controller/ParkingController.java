package one.digitalinnovation.cloudparking.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;
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
	public ResponseEntity<List<ParkingDTO>> findAll(){
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		Parking parking = parkingService.findById(id);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
		Parking parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.create(parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PostMapping("/exit/{id}")
	public ResponseEntity<ParkingDTO> exit(@PathVariable String id){
		Parking parking = parkingService.exit(id);
		return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
	}
	
	@PutMapping("/udpate/{id}")
	public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto){
		Parking parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.update(id, parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ParkingDTO> delete(@PathVariable String id){
		parkingService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
