package app.location.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Weather {
    private Coord coord;
    private Main main;
    private String name;
    private int cod;
}
