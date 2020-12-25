package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.UserAuthInput;
import co.hrsquare.bindad.mapper.IUserMapper;
import co.hrsquare.bindad.model.auth.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class UserService {

    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkUsernameExists(String username) {
        return userMapper.findByUsername(username) != null;
    }

    public String createNewUser(UserAuthInput input) {
        User newUser = new User();
        newUser.setUsername(input.getUsername());
        newUser.setPassword(passwordEncoder.encode(input.getPassword()));
        newUser.setAuthorities(toCsv(input.getAuthorities()));
        newUser.setUpdatedBy(-1);
        newUser.setUpdatedTime(LocalDateTime.now());

        userMapper.insert(newUser);
        log.info("Successfully created new user {}", newUser.getUsername());

        return "SUCCESS";
    }

    private static String toCsv(String[] authorities) {
        if (authorities == null) {
            return "";
        }

        return String.join(",", authorities);
    }

    public String removeUser(String username) {
        //check user exists
        if (!checkUsernameExists(username)){
            log.warn("Asked to delete a non-existing user: {}", username);
            return "SUCCESS";
        }

        userMapper.markDeleted(username, -1, LocalDateTime.now());
        log.info("Successfully deleted user: {}", username);

        return "SUCCESS";
    }

    public User loadUserAuthDetails(String username) {
        return userMapper.findByUsername(username);
    }
}
