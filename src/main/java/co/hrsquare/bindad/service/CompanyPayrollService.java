package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.CompanyPayrollInput;
import co.hrsquare.bindad.mapper.*;
import co.hrsquare.bindad.model.company.Company;
import co.hrsquare.bindad.model.company.payroll.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Slf4j
public class CompanyPayrollService {
    private final ICompanyMapper companyMapper;
    private final IPayrollPaymentMethodMapper payrollPaymentMethodMapper;
    private final IRtiSenderTypeMapper rtiSenderTypeMapper;
    private final IEmployeeMapper employeeMapper;
    private final ICompanyPayrollMapper companyPayrollMapper;
    private final DataStore dataStore;

    public CompanyPayrollService(ICompanyMapper companyMapper,
                                 IPayrollPaymentMethodMapper payrollPaymentMethodMapper,
                                 IRtiSenderTypeMapper rtiSenderTypeMapper,
                                 IEmployeeMapper employeeMapper,
                                 ICompanyPayrollMapper companyPayrollMapper,
                                 DataStore dataStore) {
        this.companyMapper = companyMapper;
        this.payrollPaymentMethodMapper = payrollPaymentMethodMapper;
        this.rtiSenderTypeMapper = rtiSenderTypeMapper;
        this.employeeMapper = employeeMapper;
        this.companyPayrollMapper = companyPayrollMapper;
        this.dataStore = dataStore;
    }

    public String editPayroll(CompanyPayrollInput input) {
        Company co = companyMapper.findByFullName(input.getCompanyFullName());
        if (co == null) {
            return "Cannot find company";
        }

        PayFrequency payFrequency = PayFrequency.valueOf(input.getPayFrequency());
        PayrollMonthly payrollMonthly = null;
        PayrollWeekly payrollWeekly = null;
        PayrollMultiWeekly payrollTwoWeekly = null;
        PayrollMultiWeekly payrollFourWeekly = null;
        if (PayFrequency.MONTHLY == payFrequency) {
            MonthlyPayPeriod monthlyPayPeriod = MonthlyPayPeriod.valueOf(input.getMonthlyPayPeriod());

            payrollMonthly = PayrollMonthly.builder()
                    .monthlyPayPeriod(monthlyPayPeriod)
                    .build();

            if (MonthlyPayPeriod.SPECIFIC == monthlyPayPeriod) {
                payrollMonthly.setCustomPeriodStartDate(input.getCustomPeriodStartDate());
            }

            if (input.isUseLastWorkingDay()) {
                payrollMonthly.setUseLastWorkingDay(true);
            } else if (input.isUseSpecificDate()) {
                payrollMonthly.setUseSpecificDate(true);
                payrollMonthly.setSpecificDateDay(input.getSpecificDateDay());
                payrollMonthly.setSpecificDateDayCurrentMonth(input.isSpecificDateDayCurrentMonth());
                payrollMonthly.setSpecificDateBringForwardWeekend(input.isBringForwardWeekend());
            } else if (input.isSpecificWeekday()) {
                payrollMonthly.setSpecificWeekday(true);
                payrollMonthly.setSpecificWeekdayDate(input.getSpecificWeekdayDate());
                payrollMonthly.setSpecificWeekdayDay(input.getSpecificWeekdayDay());
                payrollMonthly.setSpecificWeekdayBringForwardWeekend(input.isBringForwardWeekend());
            }
        } else if (PayFrequency.WEEKLY == payFrequency) {
            payrollWeekly = PayrollWeekly.builder()
                    .weeklyPayPeriod(WeeklyPayPeriod.valueOf(input.getWeeklyPayPeriod()))
                    .weeklyPayDay(input.getWeeklyPayDay())
                    .weeklyPayDayCurrentWeek(input.isWeeklyPayDayCurrentWeek())
                    .bringForwardWeekend(input.isBringForwardWeekend())
                    .build();
        } else if (PayFrequency.TWO_WEEKLY == payFrequency) {
            payrollTwoWeekly = PayrollMultiWeekly.builder()
                    .startDateCurrentPeriod(input.getStartDateCurrentPeriod())
                    .payDateCurrentPeriod(input.getPayDateCurrentPeriod())
                    .bringForwardWeekend(input.isBringForwardWeekend())
                    .build();
        } else if (PayFrequency.FOUR_WEEKLY == payFrequency) {
            payrollFourWeekly = PayrollMultiWeekly.builder()
                    .startDateCurrentPeriod(input.getStartDateCurrentPeriod())
                    .payDateCurrentPeriod(input.getPayDateCurrentPeriod())
                    .bringForwardWeekend(input.isBringForwardWeekend())
                    .build();
        }

        PayrollPaymentMethod paymentMethod = payrollPaymentMethodMapper.findByName(input.getPaymentMethod());

        PayeDetails payeDetails = PayeDetails.builder()
                .payeReference(input.getPayeReference())
                .accountingOfficeReference(input.getAccountingOfficeReference())
                .rtiSenderType(rtiSenderTypeMapper.findByName(input.getRtiSenderType()))
                .rtiEncodedPassword(Optional.ofNullable(input.getRtiPassword()).map(p -> Base64.encodeBase64String(p.getBytes(StandardCharsets.UTF_8))).orElse(null))
                .sautr(input.getSautr())
                .corporationTaxReference(input.getCorporationTaxReference())
                .hmrcOfficeName(input.getHmrcOfficeName())
                .hmrcOfficeTelephone(input.getHmrcOfficeTelephone())
                .smallEmployerRelief(input.isSmallEmployerRelief())
                .payrollGivingReference(input.getPayrollGivingReference())
                .companyPayrollContactEmployeeId(Optional.ofNullable(input.getCompanyPayrollContactEmployeeId()).map(employeeMapper::findIdByUuid).orElse(null))
                .payrollService(Optional.ofNullable(input.getPayrollService()).map(PayrollService::valueOf).orElse(null))
                .payrollServiceName(input.getPayrollServiceName())
                .payrollServiceContact(input.getPayrollServiceContact())
                .payrollServiceTelephone(input.getPayrollServiceTelephone())
                .payrollServiceEmail(input.getPayrollServiceEmail())
                .build();


        CompanyPayroll companyPayroll = CompanyPayroll.builder()
                .payFrequency(payFrequency)
                .payrollMonthly(payrollMonthly)
                .payrollWeekly(payrollWeekly)
                .payrollTwoWeekly(payrollTwoWeekly)
                .payrollFourWeekly(payrollFourWeekly)
                .authorisationDeadlineInDays(input.getAuthorisationDeadlineInDays())
                .authorisationDeadlineIsWorking(input.isAuthorisationDeadlineIsWorking())
                .paymentMethod(paymentMethod)
                .payeDetails(payeDetails)
                .pensionStagingCorporationDate(input.getPensionStagingCorporationDate())
                .client(co.getClient())
                .company(co)
                .deleted(false)
                .updatedBy(-1)
                .updatedTime(LocalDateTime.now())
                .build();


        //delete
        companyPayrollMapper.deleteByClientAndCompanyId(co.getClient().getId(), co.getId());

        //and insert
        dataStore.save(ICompanyPayrollMapper.class, companyPayroll);

        return "SUCCESS";
    }
}
