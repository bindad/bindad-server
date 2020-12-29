package co.hrsquare.bindad.model.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientContract {

    private ClientContractType clientContractType;
    private ContractStatus contractStatus;
    private byte[] clientContract;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;

}
