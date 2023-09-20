package one.digitalinnovation.cloudparking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import one.digitalinnovation.cloudparking.exception.ParkingNotFoundException;
import one.digitalinnovation.cloudparking.model.Parking;

@Service
public class ParkingService {

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
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public Parking findById(String id) {
		Parking parking = parkingMap.get(id);
		if (parking==null) {
			throw new ParkingNotFoundException(id);
		}
		return parking;
	}

	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		return parkingCreate;
	}
	
}
