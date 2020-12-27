package co.hrsquare.bindad.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents someone with access to the system. Can be a client or system admin.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private long id;
    private String username;
    private String password;
    private String authorities;
    private boolean deleted;
    private long updatedBy;
    private LocalDateTime updatedTime;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
