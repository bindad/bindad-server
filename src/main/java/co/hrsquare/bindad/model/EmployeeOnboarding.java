package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeOnboarding {

    private Employee employee;
    private OnboardingStatus onboardingStatus;
    private EmployeeOnboardingAudit onboardingAudit;

}
