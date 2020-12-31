package co.hrsquare.bindad.model.organisation;

import co.hrsquare.bindad.model.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    private long id;

    private String publicId;
    private String fullName;
    private String shortName;

    private Organisation organisation;
    private Client client;

    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

}
