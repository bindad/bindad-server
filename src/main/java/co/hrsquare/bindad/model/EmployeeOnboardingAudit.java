package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeOnboardingAudit implements IAudit {

    private final AuditType auditType = AuditType.EmployeeOnboarding;

    private ISystemUser createdBy;
    private LocalDateTime createdTime;
    private List<AuditEntry> auditEntries;

    @Override
    public AuditType getAuditType() {
        return auditType;
    }
}
