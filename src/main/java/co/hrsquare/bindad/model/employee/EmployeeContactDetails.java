package co.hrsquare.bindad.model.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeContactDetails {

    private FullNameDetails fullNameDetails;
    private EmailTelephone workContact;
    private EmailTelephone homeContact;

}
