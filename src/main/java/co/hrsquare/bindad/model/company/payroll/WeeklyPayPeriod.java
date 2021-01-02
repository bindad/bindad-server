package co.hrsquare.bindad.model.company.payroll;

import java.util.Arrays;
import java.util.Objects;

public enum WeeklyPayPeriod {
    MON_SUN("Mon - Sun"),
    TUE_MON("Tue - Mon"),
    WED_TUE("Wed - Tue"),
    THU_WED("Thu - Wed"),
    FRI_THU("Fri - Thu"),
    SAT_FRI("Sat - Fri"),
    SUN_SAT("Sun - Sat"),
    ;

    private final String desc;

    WeeklyPayPeriod(String desc) {
        this.desc = desc;
    }

    public static WeeklyPayPeriod from(String desc) {
        Objects.requireNonNull(desc);
        return Arrays.stream(values())
                .filter(p -> desc.equals(p.desc))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }


}
