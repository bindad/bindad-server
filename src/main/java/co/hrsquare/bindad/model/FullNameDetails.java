package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FullNameDetails {

    private Title title;
    private String firstName;
    private String knownAs;
    private String middleInitial;
    private String lastName;

}
