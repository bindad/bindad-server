package co.hrsquare.bindad.model.employee;

import co.hrsquare.bindad.model.client.Client;
import co.hrsquare.bindad.model.company.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EmployeeWeek {
    private long id;

    private HoursForDay monday;
    private HoursForDay tuesday;
    private HoursForDay wednesday;
    private HoursForDay thursday;
    private HoursForDay friday;
    private HoursForDay saturday;
    private HoursForDay sunday;

    private Company company;
    private Client client;
    private Employee employee;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;
}
