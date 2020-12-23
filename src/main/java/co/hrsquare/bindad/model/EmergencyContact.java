package co.hrsquare.bindad.model;

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
