package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticePeriod {

    private boolean statutory;
    private int periodInMonths;

}
