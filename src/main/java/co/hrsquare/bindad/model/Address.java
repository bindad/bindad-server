package co.hrsquare.bindad.model;

import co.hrsquare.bindad.controller.input.AddressInput;
import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.employee.Employee;
import co.hrsquare.bindad.model.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    private long id;

    private AddressType type;

    private String addressShortCutName;
    private String line1;
    private String line2;
    private String line3;
    private String town;
    private String country;
    private String postCode;

    private Client client;
    private Company company;
    private Employee employee;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

    public static Address fromAddressInput(AddressInput addressInput, Company co) {
        if (addressInput == null) {
            return null;
        }

        return Address.builder()
                .type(AddressType.COMPANY)
                .addressShortCutName(addressInput.getAddressShortCutName())
                .line1(addressInput.getLine1())
                .line2(addressInput.getLine2())
                .line3(addressInput.getLine3())
                .town(addressInput.getTown())
                .country(addressInput.getCountry())
                .postCode(addressInput.getPostCode())
                .client(co.getClient())
                .company(co)
                .employee(null)
                .deleted(false)
                .updatedBy(-1)
                .updatedTime(LocalDateTime.now())
                .build();
    }

}
