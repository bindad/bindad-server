package co.hrsquare.bindad.mapper;

import co.hrsquare.bindad.model.auth.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("local")
class IUserMapperITest {
    private static final String USERNAME = "dummy@dummy.co";

    @Autowired
    private IUserMapper userMapper;

    @Test
    void testInsertSelectAndDelete() {
        User user = makeUser();
        userMapper.insert(user);

        assertEquals(USERNAME, userMapper.findByUsername(USERNAME).getUsername());

        userMapper.deleteByUsername(USERNAME);
        assertNull(userMapper.findByUsername(USERNAME));
    }

    private User makeUser() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword(new BCryptPasswordEncoder().encode("raw-password"));
        user.setAuthorities("ROLE_1,ROLE_2");
        return user;
    }
}