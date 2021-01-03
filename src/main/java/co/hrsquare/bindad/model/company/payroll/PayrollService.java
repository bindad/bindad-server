package co.hrsquare.bindad.model.company.payroll;

import java.util.Arrays;
import java.util.Objects;

public enum PayrollService {

    InHouse("In House"),
    OurAccountant("Our Accountant"),
    Bureau("Bureau"),
    Xero("Xero"),
    QuickBooksOnline("Quick Books Online"),
    Sage("Sage"),
    Other("Other")
    ;

    private final String desc;

    PayrollService(String desc) {
        this.desc = desc;
    }

    public static PayrollService from(String desc) {
        Objects.requireNonNull(desc);
        return Arrays.stream(values())
                .filter(p -> desc.equals(p.desc))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }


}
