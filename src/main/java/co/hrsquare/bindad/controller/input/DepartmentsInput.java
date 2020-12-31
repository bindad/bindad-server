package co.hrsquare.bindad.controller.input;


import co.hrsquare.bindad.controller.input.patch.ObjectPatch;
import co.hrsquare.bindad.model.organisation.Department;
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

    private String orgFullName;
    private List<DepartmentInput> departments;

}
