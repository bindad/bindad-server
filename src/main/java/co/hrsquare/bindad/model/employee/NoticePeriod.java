package co.hrsquare.bindad.model.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticePeriod {

    private boolean statutory;
    private int periodInWeeks;
    private int periodInMonths;

}
