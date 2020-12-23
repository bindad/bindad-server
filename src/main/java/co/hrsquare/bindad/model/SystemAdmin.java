package co.hrsquare.bindad.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemAdmin implements ISystemUser {

    //some system id
    private String id;
    private final Role role = Role.SysAdmin;

}
