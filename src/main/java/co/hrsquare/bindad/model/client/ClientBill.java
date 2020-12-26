package co.hrsquare.bindad.model.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ClientBill {

    //system id
    private long id;
    private long clientId;

    private String invoiceId;
    private double invoiceAmount;
    private LocalDate invoiceDate;
    private LocalDate payByDate;
    private LocalDate paidDate;
    private PaymentMethod paymentMethod;

    private byte[] billContents;

}
