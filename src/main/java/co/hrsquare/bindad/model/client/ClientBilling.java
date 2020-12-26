package co.hrsquare.bindad.model.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ClientBilling {

    private List<ClientBill> clientBills;


}
