package co.hrsquare.bindad.model.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullNameDetails {

    private Title title;
    private String firstName;
    private String knownAs;
    private String middleInitial;
    private String lastName;

    public String fullName() {
        return title + ". "
                + firstName + " "
                + Optional.ofNullable(knownAs).map(s -> " (" + s + ") ").orElse("")
                + Optional.ofNullable(middleInitial).map(s -> middleInitial + ". ").orElse("")
                + lastName;
    }

}
