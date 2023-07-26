package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;

import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage.FileSystemStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/*","/js/*","/signup","/checkUserName").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) ->form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) ->logout.permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService (DataSource datasource) {
        return new JdbcUserDetailsManager(datasource);
    }
    @Bean
    CommandLineRunner init(FileSystemStorageService storageService) {
        return (args) -> {
            //storageService.deleteAll();
            storageService.init();
        };
    }
}
