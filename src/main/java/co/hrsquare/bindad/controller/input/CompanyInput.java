package co.hrsquare.bindad.controller.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyInput {

    private String tradingName;
    private String registeredName;
    private String website;
    private String contactTelephone;
    private String registeredCharityNumber;
    private String vatNumber;
    private boolean partnership;

    private FullNameDetailsInput adminNameDetails;
    private EmailTelephoneInput adminContactDetails;

    private boolean financeSameAsAdmin;
    private FullNameDetailsInput financeNameDetails;
    private EmailTelephoneInput financeContactDetails;

    private DepartmentsInput departmentsInput;

    private AddressInput primaryAddress;
    private AddressInput additionalAddress1;
    private AddressInput additionalAddress2;
    private AddressInput additionalAddress3;
    private AddressInput additionalAddress4;
    private AddressInput additionalAddress5;

}
