package co.hrsquare.bindad.service.auth;

import co.hrsquare.bindad.controller.input.UserAuthInput;
import co.hrsquare.bindad.model.auth.User;
import co.hrsquare.bindad.service.UserService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BindadUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public BindadUserDetailsService(UserService userService) {
        this.userService = Objects.requireNonNull(userService);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userService.checkUsernameExists("admin")) {
            //TODO REMOVE before going to PROD :-)
            UserAuthInput input = new UserAuthInput();
            input.setUsername("admin");
            input.setPassword("badmin");
            userService.createNewSuperUser(input);
        }

        User user = userService.loadUserAuthDetails(username);
        if (user == null) {
            throw new UsernameNotFoundException("User: " + username + " not found");
        }

        return toUserDetails(user);
    }

    private UserDetails toUserDetails(User user) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                String authorities = user.getAuthorities();
                if (authorities == null) {
                    return Collections.emptyList();
                }

                String[] tokens = authorities.split(",");
                if (tokens.length == 0) {
                    return Collections.emptyList();
                }

                return Arrays.stream(tokens)
                        .filter(StringUtils::isNotEmpty)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

}
