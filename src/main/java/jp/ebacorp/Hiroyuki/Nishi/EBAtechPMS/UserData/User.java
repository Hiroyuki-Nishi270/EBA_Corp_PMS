package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.UserData;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//User(会員)のエンティティクラス。DB上のレコードをJavaのクラスにしたようなもの
@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String password;

}
