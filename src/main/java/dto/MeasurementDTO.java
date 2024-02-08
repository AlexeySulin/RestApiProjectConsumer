package dto;

public class MeasurementDTO {
    private double value;

    private boolean raining;

    private SensorDTO sensor;

    public MeasurementDTO(double value, boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensorOwner() {
        return sensor;
    }

    public void setSensorOwner(SensorDTO sensorOwner) {
        this.sensor = sensor;
    }
}
