package co.hrsquare.bindad.model.client;

import co.hrsquare.bindad.model.employee.EmailTelephone;
import co.hrsquare.bindad.model.employee.FullNameDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Client {

    private long id;
    private FullNameDetails clientNameDetails;
    private EmailTelephone clientContactDetails;
    private ClientContract clientContract;
    private ClientBilling clientBilling;

}
