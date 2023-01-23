package pl.achrzanowski.moneymanagementregistrationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .mvcMatchers("/captcha").hasAuthority("SCOPE_registration.access")
                .mvcMatchers("/registration").hasAuthority("SCOPE_registration.access")
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt();
        return httpSecurity.build();
    }

}
