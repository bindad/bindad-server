package co.hrsquare.bindad.service;

import co.hrsquare.bindad.controller.input.UserAuthInput;
import co.hrsquare.bindad.mapper.DataStore;
import co.hrsquare.bindad.mapper.IUserMapper;
import co.hrsquare.bindad.model.auth.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
public class UserService {

    private final DataStore dataStore;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(final DataStore dataStore,
                       final IUserMapper userMapper,
                       final PasswordEncoder passwordEncoder) {
        this.dataStore = Objects.requireNonNull(dataStore);
        this.userMapper = Objects.requireNonNull(userMapper);
        this.passwordEncoder = Objects.requireNonNull(passwordEncoder);
    }

    public boolean checkUsernameExists(String username) {
        return userMapper.findByUsername(username) != null;
    }

    public long createNewUser(UserAuthInput input) {
        User newUser = new User();
        newUser.setUsername(input.getUsername());
        newUser.setPassword(passwordEncoder.encode(input.getPassword()));
        newUser.setAuthorities(toCsv(input.getAuthorities()));
        newUser.setUpdatedBy(-1);
        newUser.setUpdatedTime(LocalDateTime.now());

        userMapper.insert(newUser);
        log.info("Successfully created new user {}", newUser.getUsername());

        return userMapper.findByUsername(newUser.getUsername()).getId();
    }

    private static String toCsv(String[] authorities) {
        if (authorities == null) {
            return "";
        }

        return String.join(",", authorities);
    }

    public String resetPassword(UserAuthInput input) {
        if (!checkUsernameExists(input.getUsername())) {
            log.warn("USer {} does not exist", input.getUsername());
            return "ERROR";
        }

        userMapper.resetPassword(input.getUsername(), passwordEncoder.encode(input.getPassword()));

        return "SUCCESS";
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

    public User save(String username, String rawPassword, String authorities) {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .authorities(authorities)
                .build();

        user.setDeleted(false);
        user.setUpdatedBy(-1);
        user.setUpdatedTime(LocalDateTime.now());

        dataStore.save(IUserMapper.class, user);
        return user;
    }
}
