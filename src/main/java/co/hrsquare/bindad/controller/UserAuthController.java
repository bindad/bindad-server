package co.hrsquare.bindad.controller;

import co.hrsquare.bindad.controller.input.UserAuthInput;
import co.hrsquare.bindad.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = "/auth",
        headers = {"Accept=application/json"},
        produces = {APPLICATION_JSON_VALUE}
)
@Slf4j
public class UserAuthController {

    private final UserService userService;

    public UserAuthController(UserService userService) {
        this.userService = Objects.requireNonNull(userService);
    }

    @PostMapping("/checkUserExists")
    public String checkUserExists(String username) {
        Objects.requireNonNull(username);

        log.info("Handling checkUserExists for {}", username);
        try {
            boolean exists = userService.checkUsernameExists(username);

            log.info("Done checkUserExists for {}", username);
            return exists ? "TRUE" : "FALSE";
        } catch (Exception e) {
            log.error("Error in checkUserExists", e);
            return "ERROR";
        }
    }

    @PostMapping("/newSuperUser")
    public String createSuperUser(UserAuthInput input) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(input.getUsername());
        Objects.requireNonNull(input.getPassword());

        log.info("Handling createSuperUser for {}/{}/{}", input.getUsername(), "*****", input.getAuthorities());
        try {
            userService.createNewSuperUser(input);
        } catch (Exception e) {
            log.error("Error in createSuperUser", e);
            return "ERROR";
        }
        log.info("Done createSuperUser for {}/{}/{}", input.getUsername(), "*****", input.getAuthorities());
        return "SUCCESS";
    }

    @PostMapping("/resetPassword")
    public String resetUserPassword(UserAuthInput input) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(input.getUsername());
        Objects.requireNonNull(input.getPassword());

        log.info("Handling resetUserPassword for {}/{}/{}", input.getUsername(), "*****", input.getAuthorities());
        try {
            userService.resetPassword(input);
        } catch (Exception e) {
            log.error("Error in resetUserPassword", e);
            return "ERROR";
        }
        log.info("Done resetUserPassword for {}/{}/{}", input.getUsername(), "*****", input.getAuthorities());
        return "SUCCESS";
    }

    @PostMapping("/removeUser")
    public String removeUser(String username) {
        //for operational support only!

        Objects.requireNonNull(username);

        log.info("Handling removeUser for {}", username);
        try {
            userService.removeUser(username);
        } catch (Exception e) {
            log.error("Error in removeUser", e);
            return "ERROR";
        }
        log.info("Done removeUser for {}", username);
        return "SUCCESS";
    }


}
