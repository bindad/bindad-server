package co.hrsquare.bindad.controller.input;

import co.hrsquare.bindad.model.employee.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullNameDetailsInput {

    private Title title;
    private String firstName;
    private String knownAs;
    private String middleInitial;
    private String lastName;

}
