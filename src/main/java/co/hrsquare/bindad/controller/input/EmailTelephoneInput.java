package co.hrsquare.bindad.controller.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailTelephoneInput {

    private String email;
    private String telephone;
    private String telephoneExt;
    private String mobile;

}
