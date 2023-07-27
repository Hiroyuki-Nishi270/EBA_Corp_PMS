package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
@RequestMapping("/")
public class UserRestController {
    @Autowired
    DataSource dataSource;

    @GetMapping("/checkUserName")
    public int getCheckUserName(@RequestParam("username") String username){
        ///System.out.println(username);

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        if(users.userExists(username)){
            //System.out.println("1");
            return 1;
        }else{
            //System.out.println("0");
            return 0;
        }

    }
}
