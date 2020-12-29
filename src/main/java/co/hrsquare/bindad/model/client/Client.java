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
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    private long id;
    private String publicId;
    private FullNameDetails clientNameDetails;
    private EmailTelephone clientContactDetails;
    private ClientContract clientContract;
    private ClientBilling clientBilling;
    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

    public static Client createNewForDemo(String title, String firstName, String lastName,
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
                .contractStatus(ContractStatus.ACTIVE)
                .contractStartDate(LocalDate.now())
                .contractEndDate(LocalDate.now().plus(14, ChronoUnit.DAYS))
                .build();

        Client c = new Client();
        c.setPublicId(generatePublicId());
        c.setClientNameDetails(fullNameDetails);
        c.setClientContactDetails(emailTelephone);
        c.setClientContract(clientContract);
        c.setDeleted(false);
        c.setUpdatedBy(-1);
        c.setUpdatedTime(LocalDateTime.now());

        return c;
    }

    private static String generatePublicId() {
        //16 digit random id
        long start = 1000000000000000L;
        long end = 9999999999999999L;

        long range = end - start + 1;
        long fraction = (long)(range * new Random().nextDouble());
        return String.valueOf(start + fraction);
    }


    public static Client createNewUpgradedFromDemo(Client demoClient, LocalDate contractStartDate) {
        FullNameDetails fullNameDetails = FullNameDetails.builder()
                .title(Title.valueOf(demoClient.getClientNameDetails().getTitle().name()))
                .firstName(demoClient.getClientNameDetails().getFirstName())
                .lastName(demoClient.getClientNameDetails().getLastName())
                .build();

        EmailTelephone emailTelephone = EmailTelephone.builder()
                .email(demoClient.getClientContactDetails().getEmail())
                .telephone(demoClient.getClientContactDetails().getTelephone())
                .build();

        ClientContract clientContract = ClientContract.builder()
                .clientContractType(ClientContractType.Live)
                .contractStatus(ContractStatus.ACTIVE)
                .contractStartDate(contractStartDate)
                .build();

        Client c = new Client();
        //new id generated here, so we discard demo id
        c.setPublicId(generatePublicId());
        c.setClientNameDetails(fullNameDetails);
        c.setClientContactDetails(emailTelephone);
        c.setClientContract(clientContract);
        c.setDeleted(false);
        c.setUpdatedBy(-1);
        c.setUpdatedTime(LocalDateTime.now());

        return c;
    }
}
