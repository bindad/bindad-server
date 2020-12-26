package co.hrsquare.bindad.model.organisation;

import co.hrsquare.bindad.model.client.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Department {

    private long id;

    private String publicId;
    private String fullName;
    private String shortName;

    private Organisation organisation;
    private Client client;

}
