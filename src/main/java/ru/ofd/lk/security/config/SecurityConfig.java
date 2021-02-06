package ru.ofd.lk.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ofd.lk.security.auth.jwt.JwtConfigurer;
import ru.ofd.lk.security.auth.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider provider;
    private static final String LOGIN_PATH = "/lk/api/auth/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Bean

    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return new AppAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers(LOGIN_PATH).permitAll()
                .anyRequest().authenticated().and()
                .apply(new JwtConfigurer(provider)).and()
                .exceptionHandling().accessDeniedPage("/accessDenied.jsp");
    }
}
