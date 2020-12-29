package co.hrsquare.bindad.controller.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientUpgradeInput {

    private String clientPublicId;
    private String clientEmail;
    private String companyName;
    private LocalDate contractStartDate;
    private boolean forcePastContractStart;


}
