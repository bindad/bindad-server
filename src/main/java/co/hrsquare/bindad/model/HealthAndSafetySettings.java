package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealthAndSafetySettings {

    private boolean appointedPerson;
    private boolean fireWarden;
    private boolean firstAider;
    private boolean keyHolder;

}
