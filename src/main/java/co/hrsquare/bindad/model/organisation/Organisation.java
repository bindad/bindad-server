package co.hrsquare.bindad.model.organisation;

import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Organisation {

    //system id
    private String id;

    private String shortName;
    private String fullName;
    private Address primaryAddress;
    private List<Address> allAddresses;

    private List<Department> departments;

    private Client client;

}
