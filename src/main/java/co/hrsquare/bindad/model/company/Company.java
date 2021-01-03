package co.hrsquare.bindad.model.company;

import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.Address;
import co.hrsquare.bindad.model.EmailTelephone;
import co.hrsquare.bindad.model.FullNameDetails;
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
public class Company {

    //system id
    private long id;

    //key
    private String fullName;

    private String shortName;
    private String publicId;

    //additional company info
    private String tradingName;
    private String registeredName;
    private String website;
    private String contactTelephone;
    private String registeredCharityNumber;
    private String vatNumber;
    private boolean partnership;

    private FullNameDetails adminNameDetails;
    private EmailTelephone adminContactDetails;

    private boolean financeSameAsAdmin;
    private FullNameDetails financeNameDetails;
    private EmailTelephone financeContactDetails;

    private Address primaryAddress;
    private Address additionalAddress1;
    private Address additionalAddress2;
    private Address additionalAddress3;
    private Address additionalAddress4;
    private Address additionalAddress5;

    private List<Department> departments;

    private Client client;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

}
