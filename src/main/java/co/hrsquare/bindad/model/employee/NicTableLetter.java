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
public class NicTableLetter {
/*
    A_Standard,
    B_MarriedWomenAndWidows,
    C_EmployeesOverStatePensionAge,
    J_EmployeesDeferNI,
    H_Apprentice,
    M_EmployeesUnder21,
    Z_EmployeesUnder21DeferNI
*/

    private long id;

    private String nicLetter;
    private String nicLetterDescription;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;
}
