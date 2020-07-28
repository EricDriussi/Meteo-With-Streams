package model;
import java.time.LocalDate;

public class DataReg {
	
	private LocalDate date;
	private String station;
	private String province;
	private HourTemp max;
	private HourTemp min;
	private float rain;
	
	public DataReg() {}
	
	public DataReg(LocalDate date, String station, String province, HourTemp max, HourTemp min, float rain) {
		this.date = date;
		this.station = station;
		this.province = province;
		this.max = max;
		this.min = min;
		this.rain = rain;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public HourTemp getMax() {
		return max;
	}

	public void setMax(HourTemp max) {
		this.max = max;
	}

	public HourTemp getMin() {
		return min;
	}

	public void setMin(HourTemp min) {
		this.min = min;
	}

	public float getRain() {
		return rain;
	}

	public void setRain(float rain) {
		this.rain = rain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + Float.floatToIntBits(rain);
		result = prime * result + ((station == null) ? 0 : station.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataReg other = (DataReg) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (Float.floatToIntBits(rain) != Float.floatToIntBits(other.rain))
			return false;
		if (station == null) {
			if (other.station != null)
				return false;
		} else if (!station.equals(other.station))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataReg [date=" + date + ", station=" + station + ", province=" + province + ", max=" + max + ", min="
				+ min + ", rain=" + rain + "]";
	}

	
	
}
