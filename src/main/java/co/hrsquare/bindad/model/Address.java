package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

    private String line1;
    private String line2;
    private String line3;
    private String town;
    private String country;
    private String postCode;


}
