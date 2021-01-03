package co.hrsquare.bindad.model.employee;

import co.hrsquare.bindad.model.EmailTelephone;
import co.hrsquare.bindad.model.FullNameDetails;
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
