package co.hrsquare.bindad.model.company.payroll;

import co.hrsquare.bindad.model.Address;
import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PensionScheme {

    private long id;

    private Client client;
    private Company company;

    private String name;
    private String provider;
    private String schemeReference;
    private PensionContributionType contributionType;
    private double contributionThresholdLower;
    private double contributionThresholdUpper;
    private double employeeContributionPercentage;
    private double employerContributionPercentage;
    private boolean employerContributionPerEmployee;
    private PensionTaxationType pensionTaxationType;
    private boolean autoEnrolmentCompatible;
    private boolean autoEnrolmentDelayStartNever;
    private boolean autoEnrolmentDelayStartAlways;
    private boolean autoEnrolmentDelayStartPerEmployee;
    private Address pensionAddress;
    private boolean defaultScheme;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

}
