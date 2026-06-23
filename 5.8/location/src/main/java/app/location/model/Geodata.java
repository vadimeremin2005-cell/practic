package app.location.model;

public class Geodata {
    private String name;
    private Double longitude;
    private Double latitude;

    public Geodata() {
    }

    public Geodata(Location location) {
        this.name = location.getName();
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
