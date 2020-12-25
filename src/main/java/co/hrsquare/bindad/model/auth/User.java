package co.hrsquare.bindad.model.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User {

    private long id;
    private String username;
    private String password;
    private String authorities;
    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

}
