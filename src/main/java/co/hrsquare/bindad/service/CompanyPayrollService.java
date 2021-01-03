package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.CompanyPayrollInput;
import co.hrsquare.bindad.mapper.*;
import co.hrsquare.bindad.model.Address;
import co.hrsquare.bindad.model.company.Company;
import co.hrsquare.bindad.model.company.payroll.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static co.hrsquare.bindad.model.Address.fromAddressInput;

@Component
@Slf4j
public class CompanyPayrollService {
    private final ICompanyMapper companyMapper;
    private final IPayrollPaymentMethodMapper payrollPaymentMethodMapper;
    private final IRtiSenderTypeMapper rtiSenderTypeMapper;
    private final IEmployeeMapper employeeMapper;
    private final ICompanyPayrollMapper companyPayrollMapper;
    private final DataStore dataStore;
    private final IPensionContributionTypeMapper pensionContributionTypeMapper;
    private final IPensionTaxationTypeMapper pensionTaxationTypeMapper;
    private final IPensionSchemeMapper pensionSchemeMapper;
    private final IAddressMapper addressMapper;

    public CompanyPayrollService(ICompanyMapper companyMapper,
                                 IPayrollPaymentMethodMapper payrollPaymentMethodMapper,
                                 IRtiSenderTypeMapper rtiSenderTypeMapper,
                                 IEmployeeMapper employeeMapper,
                                 ICompanyPayrollMapper companyPayrollMapper,
                                 DataStore dataStore,
                                 IPensionContributionTypeMapper pensionContributionTypeMapper,
                                 IPensionTaxationTypeMapper pensionTaxationTypeMapper,
                                 IPensionSchemeMapper pensionSchemeMapper,
                                 IAddressMapper addressMapper) {
        this.companyMapper = companyMapper;
        this.payrollPaymentMethodMapper = payrollPaymentMethodMapper;
        this.rtiSenderTypeMapper = rtiSenderTypeMapper;
        this.employeeMapper = employeeMapper;
        this.companyPayrollMapper = companyPayrollMapper;
        this.dataStore = dataStore;
        this.pensionContributionTypeMapper = pensionContributionTypeMapper;
        this.pensionTaxationTypeMapper = pensionTaxationTypeMapper;
        this.pensionSchemeMapper = pensionSchemeMapper;
        this.addressMapper = addressMapper;
    }

    @Transactional
    public String editPayroll(CompanyPayrollInput input) {
        Company co = companyMapper.findByFullName(input.getCompanyFullName());
        if (co == null) {
            return "Cannot find company";
        }

        //payroll
        CompanyPayroll companyPayroll = buildCompanyPayrollFromInput(input, co);
        companyPayrollMapper.deleteByClientAndCompanyId(co.getClient().getId(), co.getId());
        dataStore.save(ICompanyPayrollMapper.class, companyPayroll);

        //pension schemes
        List<Long> addressIdsToDelete = pensionSchemeMapper.findAllPensionAddressIds(co.getClient().getId(), co.getId());
        addressMapper.deleteAll(addressIdsToDelete);
        pensionSchemeMapper.deleteByClientAndCompanyId(co.getClient().getId(), co.getId());
        List<PensionScheme> pensionSchemes = buildPensionSchemesFromInput(input, co);
        pensionSchemes.forEach(ps -> {
            dataStore.save(IPensionSchemeMapper.class, ps);

            if (ps.getPensionAddress() != null) {
                dataStore.save(IAddressMapper.class, ps.getPensionAddress());
            }
        });

        //benefits

        return "SUCCESS";
    }

    private List<PensionScheme> buildPensionSchemesFromInput(CompanyPayrollInput input, Company co) {
        if (input.getPensionSchemes() == null || input.getPensionSchemes().getPensionSchemeInputs() == null) {
            return Collections.emptyList();
        }

        return input.getPensionSchemes().getPensionSchemeInputs()
                .stream()
                .map(ps -> {
                    Address pensionAddress = fromAddressInput(ps.getPensionAddress(), co);
                    return PensionScheme.builder()
                            .name(ps.getName())
                            .provider(ps.getProvider())
                            .schemeReference(ps.getSchemeReference())
                            .contributionType(Optional.ofNullable(ps.getContributionType()).map(pensionContributionTypeMapper::findByName).orElse(null))
                            .contributionThresholdLower(ps.getContributionThresholdLower())
                            .contributionThresholdUpper(ps.getContributionThresholdUpper())
                            .employeeContributionPercentage(ps.getEmployeeContributionPercentage())
                            .employerContributionPercentage(ps.getEmployerContributionPercentage())
                            .employerContributionPerEmployee(ps.isEmployerContributionPerEmployee())
                            .pensionTaxationType(Optional.ofNullable(ps.getPensionTaxationType()).map(pensionTaxationTypeMapper::findByName).orElse(null))
                            .autoEnrolmentCompatible(ps.isAutoEnrolmentCompatible())
                            .autoEnrolmentDelayStartNever(ps.isAutoEnrolmentDelayStartNever())
                            .autoEnrolmentDelayStartAlways(ps.isAutoEnrolmentDelayStartAlways())
                            .autoEnrolmentDelayStartPerEmployee(ps.isAutoEnrolmentDelayStartPerEmployee())
                            .pensionAddress(pensionAddress)
                            .client(co.getClient())
                            .company(co)
                            .deleted(false)
                            .updatedBy(-1)
                            .updatedTime(LocalDateTime.now())
                            .build();
                }).collect(Collectors.toList());
    }

    private CompanyPayroll buildCompanyPayrollFromInput(CompanyPayrollInput input, Company co) {
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

        return CompanyPayroll.builder()
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
    }
}
