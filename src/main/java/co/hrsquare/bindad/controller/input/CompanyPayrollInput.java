package co.hrsquare.bindad.controller.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyPayrollInput {
    private String companyFullName;

    private String payFrequency;

    private String monthlyPayPeriod;
    private int customPeriodStartDate;
    private boolean useLastWorkingDay;
    private boolean useSpecificDate;
    private int specificDateDay;
    private boolean specificDateDayCurrentMonth;
    private boolean specificWeekday;
    private String specificWeekdayDate;
    private String specificWeekdayDay;

    private String weeklyPayPeriod;
    private String weeklyPayDay;
    private boolean weeklyPayDayCurrentWeek;

    //is 2-weekly or 4-weekly
    private boolean twoWeekly;
    private LocalDate startDateCurrentPeriod;
    private LocalDate payDateCurrentPeriod;

    private boolean bringForwardWeekend;

    private int authorisationDeadlineInDays;
    private boolean authorisationDeadlineIsWorking;

    private String paymentMethod;

    private String payeReference;
    private String accountingOfficeReference;
    private String rtiSenderType;
    private String rtiSenderId;
    private String rtiPassword;
    private String sautr;
    private String corporationTaxReference;
    private String hmrcOfficeName;
    private String hmrcOfficeTelephone;
    private boolean smallEmployerRelief;
    private String payrollGivingReference;
    private String companyPayrollContactEmployeeId;
    private String payrollService;
    private String payrollServiceName;
    private String payrollServiceContact;
    private String payrollServiceTelephone;
    private String payrollServiceEmail;

    private LocalDate pensionStagingCorporationDate;
    private PensionSchemesInput pensionSchemes;

    private CompanyBenefitsInput companyBenefits;

}
