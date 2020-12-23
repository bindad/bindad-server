package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailTelephone {

    private String email;
    private String telephone;
    private String telephoneExt;
    private String mobile;

}
