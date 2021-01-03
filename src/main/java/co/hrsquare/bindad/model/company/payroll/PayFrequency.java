package co.hrsquare.bindad.model.company.payroll;

import java.util.Arrays;
import java.util.Objects;

public enum PayFrequency {

    MONTHLY("Monthly"),
    WEEKLY("Weekly"),
    TWO_WEEKLY("2-Weekly"),
    FOUR_WEEKLY("4-Weekly");

    private final String desc;

    PayFrequency(String desc) {
        this.desc = desc;
    }

    public static PayFrequency from(String desc) {
        Objects.requireNonNull(desc);
        return Arrays.stream(values())
                .filter(p -> desc.equals(p.desc))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
