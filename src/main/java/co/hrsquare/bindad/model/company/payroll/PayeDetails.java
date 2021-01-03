package co.hrsquare.bindad.model.company.payroll;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayeDetails {

    private String payeReference;
    private String accountingOfficeReference;
    private RtiSenderType rtiSenderType;
    private String rtiSenderId;
    private String rtiEncodedPassword;
    private String sautr;
    private String corporationTaxReference;
    private String hmrcOfficeName;
    private String hmrcOfficeTelephone;
    private boolean smallEmployerRelief;
    private String payrollGivingReference;
    private Long companyPayrollContactEmployeeId;
    private PayrollService payrollService;
    private String payrollServiceName;
    private String payrollServiceContact;
    private String payrollServiceTelephone;
    private String payrollServiceEmail;


}
