package co.hrsquare.bindad.model;

import co.hrsquare.bindad.model.AddressType;
import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.employee.Employee;
import co.hrsquare.bindad.model.organisation.Organisation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

    private long id;

    private AddressType type;
    private boolean primary;

    private String addressShortCutName;
    private String line1;
    private String line2;
    private String line3;
    private String town;
    private String country;
    private String postCode;

    private Client client;
    private Organisation organisation;
    private Employee employee;

}
