package co.hrsquare.bindad.controller.input;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentsInput {

    private String coFullName;
    private List<DepartmentInput> departments;

}
