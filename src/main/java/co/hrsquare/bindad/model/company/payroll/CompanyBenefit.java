package co.hrsquare.bindad.model.company.payroll;

import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyBenefit {

    private long id;

    private String name;
    private boolean salarySacrifice;

    private Client client;
    private Company company;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

}
