package co.hrsquare.bindad.controller.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientUpgradeInput {

    private String clientPublicId;
    private String clientEmail;
    private String companyName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate contractStartDate;
    private boolean forcePastContractStart;


}
