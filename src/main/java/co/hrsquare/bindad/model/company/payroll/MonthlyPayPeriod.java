package co.hrsquare.bindad.model.company.payroll;

import java.util.Arrays;
import java.util.Objects;

public enum MonthlyPayPeriod {
    CALENDAR("Calendar months (Jan, Feb, Mar, etc)"),
    TAX("Tax months (6th to 5th)"),
    SPECIFIC("Specified monthly periods...")
    ;

    private final String desc;

    MonthlyPayPeriod(String desc) {
        this.desc = desc;
    }

    public static MonthlyPayPeriod from(String desc) {
        Objects.requireNonNull(desc);
        return Arrays.stream(values())
                .filter(p -> desc.equals(p.desc))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}
