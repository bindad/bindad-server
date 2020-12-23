package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankDetails {

    private String accountName;
    private String accountNumber;
    private String sortCode;
    private String bankName;
    private Address bankAddress;

}
