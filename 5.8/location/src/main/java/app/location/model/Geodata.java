package app.location.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Geodata {
    private String name;
    private Double longitude;
    private Double latitude;

    public Geodata(Location location) {
        this.name = location.getName();
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
    }
}
