package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class HoursForDay {

    private LocalTime amStart;
    private LocalTime amEnd;
    private LocalTime lunchStart;
    private LocalTime lunchEnd;
    private LocalTime pmStart;
    private LocalTime pmEnd;
    private boolean lunchPaid;

}
