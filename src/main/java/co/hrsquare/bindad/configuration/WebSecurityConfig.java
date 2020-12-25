package co.hrsquare.bindad.configuration;

import co.hrsquare.bindad.service.auth.BindadUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] PROTECTED_RESOURCES = new String[] {
            "/onboard/**",
            "/auth/**",
            "/swagger-ui/**"
    };

    private final BindadUserDetailsService bindadUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(BindadUserDetailsService bindadUserDetailsService,
                             PasswordEncoder passwordEncoder) {
        this.bindadUserDetailsService = bindadUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PROTECTED_RESOURCES).authenticated()
                .antMatchers("/").permitAll()
                .and()
                .httpBasic()
                .and()
                .headers().frameOptions().disable()
                .and()
                .headers().addHeaderWriter((req, res) -> res.addHeader("Cache-Control", "no-store"))
                .and()
                .headers().cacheControl().disable()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(bindadUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        auth.authenticationProvider(authProvider);
    }
}
