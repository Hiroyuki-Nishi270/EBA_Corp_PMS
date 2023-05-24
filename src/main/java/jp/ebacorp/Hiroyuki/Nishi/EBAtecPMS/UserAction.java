package jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserAction {

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder encoder;
    public void createUser(String username, String password){
        String pw = encoder.encode(password);

        UserDetails user = User.builder().username(username).password(pw).roles("USER").build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        users.createUser(user);
        //System.out.println("raw:" + password1);
        //System.out.println("encorded:" + pw );
        //System.out.println("decorded:" + (encoder.matches(password1,pw)));
    }
}
