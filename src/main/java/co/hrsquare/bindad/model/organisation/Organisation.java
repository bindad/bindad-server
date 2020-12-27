package co.hrsquare.bindad.model.organisation;

import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organisation {

    //system id
    private long id;

    private String shortName;
    private String fullName;
    private String publicId;
    private Address primaryAddress;
    private List<Address> allAddresses;

    private List<Department> departments;

    private Client client;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

}
