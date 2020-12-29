package co.hrsquare.bindad.controller.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientSummary {
    public static final ClientSummary NO_CLIENT_INFO = new ClientSummary();

    private String clientPublicId;
    private String fullContactName;
    private String emailAddress;
    private String companyName;
    private String contractType;
    private String contractStatus;


}
