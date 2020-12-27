package co.hrsquare.bindad.model.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CircumstancesAtStart {
    /*
        A_EmployeeFirstJobSince6April,
        B_EmployeeOnlyJobSince6April,
        B_EmployeeHasAnotherJob
     */

    private long id;

    private String circumstancesLetterCode;
    private String circumstancesLetterDescription;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

}
