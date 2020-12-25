package co.hrsquare.bindad.controller.input;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAuthInput {

    private String username;
    private String password;
    private String[] authorities;

}
