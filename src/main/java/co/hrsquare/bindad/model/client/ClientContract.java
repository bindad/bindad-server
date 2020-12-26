package co.hrsquare.bindad.model.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ClientContract {

    private ClientContractType clientContractType;
    private byte[] clientContract;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;

}
