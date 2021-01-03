package co.hrsquare.bindad.controller.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressInput {

    private String addressShortCutName;
    private String line1;
    private String line2;
    private String line3;
    private String town;
    private String country;
    private String postCode;


}
