package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.UserData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//User(会員)の情報登録のためのServiceクラス
//削除とパスワード変更用のメソッドも後々必要。
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // ユーザー登録用API
    public User createUser(String name, String email, String rawPassword) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        // パスワードはハッシュ化する
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

}
