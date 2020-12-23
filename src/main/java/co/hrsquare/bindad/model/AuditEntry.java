package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AuditEntry {

    private ISystemUser updatedBy;
    private LocalDateTime updatedTime;
    private String updateData;
    private String updateComment;

}
