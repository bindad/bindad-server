package co.hrsquare.bindad.model.company.payroll;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardCompanyBenefit {

    private long id;

    private String name;
    private boolean salarySacrifice;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

}
