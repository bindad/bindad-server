package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeePersonalDetails {

    private LocalDate birthDate;
    private Gender gender;

}
