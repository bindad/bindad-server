package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemAdmin implements ISystemUser {

    //some system id
    private long id;
    private final Role role = Role.SysAdmin;

}
