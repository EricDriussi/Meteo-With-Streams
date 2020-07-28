package model;

import java.time.LocalTime;

public class HourTemp {

	private float temp;
	private LocalTime time;
	
	public HourTemp() {}

	public HourTemp(float temp, LocalTime time) {
		super();
		this.temp = temp;
		this.time = time;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(temp);
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		HourTemp other = (HourTemp) obj;
		if (Float.floatToIntBits(temp) != Float.floatToIntBits(other.temp))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HourTemp [temp=" + temp + ", time=" + time + "]";
	}
	
	
	
}
