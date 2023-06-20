package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                        .requestMatchers("/css/**","/js/**","/signup").permitAll()
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

    /**
    @Bean
    public UserDetailsService user() {
        UserDetails user = User.builder()
                .username("InMemoryTest")
                .password(passwordEncoder().encode("A-a1234567"))
                .roles("USER")
                .build();
        System.out.println(passwordEncoder().encode("A-a1234567"));
        return new InMemoryUserDetailsManager(user);
    }
    **/

}
