package co.hrsquare.bindad.model.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailTelephone {

    private String email;
    private String telephone;
    private String telephoneExt;
    private String mobile;

}
