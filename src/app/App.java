package app;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.Reader;
import model.DataReg;
import model.HourTemp;
import service.Climate;
import util.PrintTemp;

public class App {

	private static Climate service;

	public static void main(String[] args) {
		
		Reader.start(args);
		
	}

	public static void printclimate(String date) {

		System.out.printf("SPANISH CLIMATE DATA FROM %s%n", date.toUpperCase());
		System.out.println("**************************************************\n\n");
		
		service = Climate.getInstance();
		
		maximaYMinimaTotal();
		maximaPorProvincias();
		minimaPorProvincias();
		mediasPorProvincias();

		datosPorProvincia("CANTABRIA");

	}

	private static void datosPorProvincia(String string) {

		System.out.println("DATA FROM SPECIFIC PROVINCE: " + string.toUpperCase());
		System.out.println("-----------------------------------------------------");
		List<DataReg> data = service.provData(string);
		data.stream().sorted(Comparator.comparing(DataReg::getStation))
				.forEach((r) -> System.out.printf(
						"Meteo station: %-35s | Max: % 6.2f (%s) | Min: % 6.2f (%s) | Rainfall: %.2f%n", r.getStation(),
						r.getMax().getTemp(), r.getMax().getTime(), r.getMin().getTemp(), r.getMin().getTime(),
						r.getRain()));

	}

	private static void mediasPorProvincias() {

		System.out.println("AVERAGE TEMPS BY PROVINCE");
		System.out.println("-----------------------------------------------------");
		Map<String, Double> avg = service.avgProv();
		avg.forEach((prov, temp) -> System.out.printf("Province: %-25s | Average: %5.2f°C%n", prov, temp));
		System.out.println();
	}

	private static void minimaPorProvincias() {

		System.out.println("MINIMAL TEMPS BY PROVINCE");
		System.out.println("-----------------------------------------------------");
		Map<String, Optional<HourTemp>> min = service.minTempByProv();
		PrintTemp.printMapProvinceTempHour(min);

	}

	private static void maximaPorProvincias() {

		System.out.println("MAXIMAL TEMPS BY PROVINCE");
		System.out.println("-----------------------------------------------------");
		Map<String, Optional<HourTemp>> max = service.maxTempByProv();
		PrintTemp.printMapProvinceTempHour(max);

	}

	private static void maximaYMinimaTotal() {

		System.out.println("SPAIN MAX AND MIN TEMPS");
		System.out.println("-----------------------------------------------------");

		Optional<DataReg> min, max;

		min = service.totalMin();
		max = service.totalMax();

		if (max.isPresent())
			System.out.printf("MAX -> Meteo station: %s (%s) | Temperature: %.2f°C | Time: %s%n",
					max.get().getStation(), max.get().getProvince(), max.get().getMin().getTemp(),
					max.get().getMin().getTime());
		else
			System.out.println("No data available for max temp");

		if (min.isPresent())
			System.out.printf("MIN -> Meteo station: %s (%s) | Temperature: %.2f°C | Time: %s%n",
					min.get().getStation(), min.get().getProvince(), min.get().getMin().getTemp(),
					min.get().getMin().getTime());
		else
			System.out.println("No data available for min temp");

		System.out.println();

	}

}
