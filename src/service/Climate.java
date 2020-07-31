package service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import model.DataReg;
import model.HourTemp;

public class Climate {

	/*
	 * Singleton
	 */
	List<DataReg> data;
	private static Climate instance;

	private Climate(List<DataReg> data) {
		this.data = data;
	}
	
	public static Climate getInstance() {
		return instance;
	}
	
	public static Climate getInstance(List<DataReg> data) {
		
		if(instance==null)  instance = new Climate(data); //instance = new Climate(Init.getAll());
		
		return instance;	
	}
	
	public static void reset() {
		instance = null;
	}
	
	/*
	 * Returns max temps grouped by province
	 */
	public Map<String, Optional<HourTemp>> maxTempByProv(){
		
		Collector<DataReg, ?, Optional<HourTemp>> c = Collectors.mapping(
						DataReg::getMax,
						Collectors.maxBy((m1, m2) -> Float.compare(m1.getTemp(), m2.getTemp()))
						);
		
		return data.stream().collect(Collectors.groupingBy(DataReg::getProvince, c));
	}


	/*
	 * Returns min temps grouped by province
	 */
	public Map<String, Optional<HourTemp>> minTempByProv(){
		
		Collector<DataReg, ?, Optional<HourTemp>> c = Collectors.mapping(
						DataReg::getMin, 
						Collectors.minBy((m1, m2) -> Float.compare(m1.getTemp(), m2.getTemp()))
						);

		return data.stream().collect(Collectors.groupingBy(DataReg::getProvince, c));
		
	}


	/*
	 * Calculates avg temps by province
	 */
	public Map<String, Double> avgProv() {

		return data.stream()
				.collect(Collectors.toMap(Function.identity(),
						(DataReg r) -> (r.getMax().getTemp() + r.getMin().getTemp()) / 2))
				.entrySet().stream()
				.collect(Collectors.groupingBy((Map.Entry<DataReg, Float> r) -> r.getKey().getProvince(),
						TreeMap::new,
						Collectors.averagingDouble(((Map.Entry<DataReg, Float> r) -> r.getValue()))));

	}


	/*
	 * Filters out data from specific province
	 */
	public List<DataReg> provData(String prov){
		
		return data.stream()
				.filter(data -> data.getProvince().equalsIgnoreCase(prov)).collect(Collectors.toList());
				
	}

	
	/*
	 * Absolute max temp
	 */
	public Optional<DataReg> totalMax(){
		
		return data.stream()
				.max((r1, r2) -> Float.compare(r1.getMax().getTemp(), r2.getMax().getTemp()));
		
	}

	

	/*
	 * Absolute min temp
	 */
	public Optional<DataReg> totalMin(){
		
		return data.stream()
				.min((r1, r2) -> Float.compare(r1.getMin().getTemp(), r2.getMin().getTemp()));
		
	}
	
}
