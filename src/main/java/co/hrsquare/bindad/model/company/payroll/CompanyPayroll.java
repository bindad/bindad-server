package co.hrsquare.bindad.model.company.payroll;

import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyPayroll {

    private long id;

    private PayFrequency payFrequency;

    private PayrollMonthly payrollMonthly;
    private PayrollWeekly payrollWeekly;
    private PayrollMultiWeekly payrollTwoWeekly;
    private PayrollMultiWeekly payrollFourWeekly;

    private int authorisationDeadlineInDays;
    //working or calendar
    private boolean authorisationDeadlineIsWorking;

    private PayrollPaymentMethod paymentMethod;

    private PayeDetails payeDetails;

    private LocalDate pensionStagingCorporationDate;
    private List<PensionScheme> pensionSchemes;

    private List<CompanyBenefit> companyBenefits;

    private Client client;
    private Company company;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

}
