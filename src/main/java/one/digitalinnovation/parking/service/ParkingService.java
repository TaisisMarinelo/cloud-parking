package one.digitalinnovation.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import one.digitalinnovation.parking.model.Parking;

@Service
public class ParkingService {
	
	//Lista de dados mock
	private static Map<String, Parking> parkingMap =  new HashMap<>();
	
	//registro
	static {
		var id = getUUID();
		var id1 = getUUID();
		Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
		Parking parking1 = new Parking(id1, "DMS-222", "RJ", "VW", "VERMELHO");
		parkingMap.put(id, parking);
		parkingMap.put(id1, parking1);
	}
	
	//pega tudo do map e transforma em uma lista
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}
	
	//gerando chave hash, removendo o - 
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public Parking findById(String id) {
		return parkingMap.get(id);
	}

	public Parking create(Parking parkingCreate) {
		String uuid =  getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		return parkingCreate;
	}

}
