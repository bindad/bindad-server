package co.hrsquare.bindad.model.client;

import co.hrsquare.bindad.model.employee.EmailTelephone;
import co.hrsquare.bindad.model.employee.FullNameDetails;
import co.hrsquare.bindad.model.employee.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    private long id;
    private FullNameDetails clientNameDetails;
    private EmailTelephone clientContactDetails;
    private ClientContract clientContract;
    private ClientBilling clientBilling;
    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

    public static Client createForDemo(String title, String firstName, String lastName,
                                       String emailAddress, String telephone) {
        FullNameDetails fullNameDetails = FullNameDetails.builder()
                .title(Title.valueOf(title))
                .firstName(firstName)
                .lastName(lastName)
                .build();

        EmailTelephone emailTelephone = EmailTelephone.builder()
                .email(emailAddress)
                .telephone(telephone)
                .build();

        ClientContract clientContract = ClientContract.builder()
                .clientContractType(ClientContractType.Demo)
                .contractStartDate(LocalDate.now())
                .contractEndDate(LocalDate.now().plus(14, ChronoUnit.DAYS))
                .build();

        Client c = new Client();
        c.setClientNameDetails(fullNameDetails);
        c.setClientContactDetails(emailTelephone);
        c.setClientContract(clientContract);
        c.setDeleted(false);
        c.setUpdatedBy(-1);
        c.setUpdatedTime(LocalDateTime.now());

        return c;
    }
}
