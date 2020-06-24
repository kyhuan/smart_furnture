package com.xupt.response;

public class DataReponse {
    private String temperature;
    private String humidity;
    private String lightIntensity;
    private String rainfall;
    private boolean flame;
    private boolean smoke;

    public DataReponse() {
    }

    public DataReponse(String temperature, String humidity, String lightIntensity, String rainfall, boolean flame, boolean smoke) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.lightIntensity = lightIntensity;
        this.rainfall = rainfall;
        this.flame = flame;
        this.smoke = smoke;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(String lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public boolean isFlame() {
        return flame;
    }

    public void setFlame(boolean flame) {
        this.flame = flame;
    }

    public boolean isSmoke() {
        return smoke;
    }

    public void setSmoke(boolean smoke) {
        this.smoke = smoke;
    }

    @Override
    public String toString() {
        return "DataReponse{" +
                "temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", lightIntensity='" + lightIntensity + '\'' +
                ", rainfall='" + rainfall + '\'' +
                ", flame=" + flame +
                ", smoke=" + smoke +
                '}';
    }
}
