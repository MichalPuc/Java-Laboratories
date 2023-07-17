
public class DataPoint {
    private String time;

    public String getTime() {
        return time;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    private double pressure;
    private double temperature;
    private double humidity;

    public DataPoint(String time, double pressure, double temperature, double humidity) {
        this.time = time;
        this.pressure = pressure;
        this.temperature = temperature;
        this.humidity = humidity;
    }

}