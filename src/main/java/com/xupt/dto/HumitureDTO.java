package com.xupt.dto;

import com.xupt.pojo.Humiture;

import java.io.Serializable;

public class HumitureDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double temperature;
	private Double humidity;

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Humiture toModel() {
		Humiture model = new Humiture();
		model.setTemperature(temperature);
		model.setHumidity(humidity);
		return model;
	}
}
