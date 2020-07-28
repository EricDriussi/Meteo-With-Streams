package util;

import java.util.Map;
import java.util.Optional;

import model.HourTemp;

public class PrintTemp {

	public static void printMapProvinceTempHour(Map<String, Optional<HourTemp>> map) {

		map.keySet().stream().sorted().forEach(province -> {
			
			HourTemp t = map.get(province).get();
			
			System.out.printf("Province: %-25s | Temperature: % 6.2f°C | Time: %s%n", province, t.getTemp(),
					t.getTime().toString());
		});
		
		System.out.println("");
	}

}