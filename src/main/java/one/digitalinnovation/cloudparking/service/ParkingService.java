package one.digitalinnovation.cloudparking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import one.digitalinnovation.cloudparking.model.Parking;

@Service
public class ParkingService {

	//List de dados mokados
	private static Map<String, Parking> parkingMap = new HashMap<>();
	
	static {
		var id1 = getUUID();
		Parking parking = new Parking(id1, "DMS-1111", "SC", "CELTA", "Preto");
		parkingMap.put(id1, parking);

	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
