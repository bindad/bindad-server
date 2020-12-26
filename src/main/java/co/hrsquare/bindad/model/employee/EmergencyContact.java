package co.hrsquare.bindad.model.employee;

import co.hrsquare.bindad.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmergencyContact {

    private String relationship;
    private FullNameDetails fullNameDetails;
    private EmailTelephone emailTelephone;
    private Address address;

}
