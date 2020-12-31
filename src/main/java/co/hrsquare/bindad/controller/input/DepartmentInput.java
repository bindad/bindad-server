package co.hrsquare.bindad.controller.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentInput {

    private String publicId;
    private String fullName;
    private String shortName;


}
