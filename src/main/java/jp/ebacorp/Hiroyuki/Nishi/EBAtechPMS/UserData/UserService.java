package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.UserData;


import jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.UserData.User;
import jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.UserData.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



//User(会員)の情報登録のためのServiceクラス
//削除とパスワード変更用のメソッドも後々必要。
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //暗号化処理のインスタンス生成
    //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // ユーザー登録用API
    public void createUser(String name, String email, String rawPassword) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        // パスワードはハッシュ化する
        //user.setPassword(passwordEncoder.encode(rawPassword));
        user.setPassword(rawPassword);
        userRepository.save(user);
    }

}
