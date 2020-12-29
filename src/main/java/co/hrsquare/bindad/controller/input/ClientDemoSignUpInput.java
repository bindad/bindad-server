package co.hrsquare.bindad.controller.input;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDemoSignUpInput {

    private String title;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String companyName;
    private String telephone;
    private String password;

    @Override
    public String toString() {
        return "{" +
                "emailAddress='" + emailAddress + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
