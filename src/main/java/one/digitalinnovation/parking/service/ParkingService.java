package one.digitalinnovation.parking.service;

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
		Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
		parkingMap.put(id, parking);
	}
	
	//pega tudo do map e transforma em uma lista
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}
	
	//gerando chave hash, removendo o - 
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
