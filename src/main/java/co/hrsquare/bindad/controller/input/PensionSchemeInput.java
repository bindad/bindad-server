package co.hrsquare.bindad.controller.input;

import co.hrsquare.bindad.model.Address;
import co.hrsquare.bindad.model.company.payroll.PensionContributionType;
import co.hrsquare.bindad.model.company.payroll.PensionTaxationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PensionSchemeInput {

    private String name;
    private String provider;
    private String schemeReference;
    private String contributionType;
    private double contributionThresholdLower;
    private double contributionThresholdUpper;
    private double employeeContributionPercentage;
    private double employerContributionPercentage;
    private boolean employerContributionPerEmployee;
    private String pensionTaxationType;
    private boolean autoEnrolmentCompatible;
    private boolean autoEnrolmentDelayStartNever;
    private boolean autoEnrolmentDelayStartAlways;
    private boolean autoEnrolmentDelayStartPerEmployee;
    private AddressInput pensionAddress;
    private boolean defaultScheme;


}
