package pl.achrzanowski.moneymanagementregistrationservice.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class RegistrationService {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void registerUser(RegistrationDTO registrationDTO){
        String createUserSql = "insert into users (username, password, enabled) values (?,?, CAST(? AS bit))";
        jdbcTemplate.update(createUserSql, ps -> {
            ps.setString(1, registrationDTO.getUsername());
            ps.setString(2, "{bcrypt}" + registrationDTO.getPassword());
            ps.setString(3, "1");

        });
        jdbcTemplate.update(JdbcUserDetailsManager.DEF_INSERT_AUTHORITY_SQL, ps -> {
            ps.setString(1, registrationDTO.getUsername());
            ps.setString(2, "USER");
        });
    }

}
