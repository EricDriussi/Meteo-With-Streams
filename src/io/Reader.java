package io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import app.App;
import model.DataReg;
import model.HourTemp;
import service.Climate;

public class Reader {

	public static Climate service;

	public static void start(String[] args) {

		if (args.length >= 2 && (args.length % 2 == 0)) {

			List<String[]> couple = IntStream.iterate(0, i -> i += 2).limit(args.length / 2)
					.mapToObj(n -> new String[] { args[n], args[n + 1] }).collect(Collectors.toList());

			couple.stream().forEach(pair -> {

				String date = pair[0];
				LocalDate local = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				Path path = Paths.get(pair[1]);
				Reader.readDataFromPath(path, local).ifPresent((list) -> {

					service = Climate.getInstance(list);

					App.printclimate(local.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
					Climate.reset();
				});
			});

		} else {
			System.err.println("Sintax Error: java -jar meteo.jar dd-mm-yyyy file1.csv [ dd-mm-yyyy file2.csv ...]");
		}
	}

	public static Optional<List<DataReg>> readDataFromPath(Path p, LocalDate date) {

		if (Files.exists(p)) {

			try (Stream<String> stream = Files.lines(p, Charset.forName("Cp1252"))) {

				return Optional.of(stream
						.map(s -> s.split(";"))
						.map(splitted -> {

					String station = splitted[0];
					String province = splitted[1];
					HourTemp max = new HourTemp(Float.parseFloat(splitted[2]),
								LocalTime.parse(splitted[3], 
								DateTimeFormatter.ofPattern("H:mm")));
					HourTemp min = new HourTemp(Float.parseFloat(splitted[4]),
								LocalTime.parse(splitted[5], 
								DateTimeFormatter.ofPattern("H:mm")));
					float rainfall = Float.parseFloat(splitted[6].replace(",", "."));

					return new DataReg(date, station, province, max, min, rainfall);
				}).collect(Collectors.toList()));

			} catch (IOException ex) {

				System.err.println(ex.getMessage());
				return Optional.empty();
			}
		} else {
			return Optional.empty();
		}

	}

}
