package co.hrsquare.bindad.model.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
