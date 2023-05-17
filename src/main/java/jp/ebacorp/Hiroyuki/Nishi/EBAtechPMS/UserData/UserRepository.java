package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.UserData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//User(会員)のリポジトリ。DBのテーブルをJavaのインターフェースにしたようなもの
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByemail(String email);

}
