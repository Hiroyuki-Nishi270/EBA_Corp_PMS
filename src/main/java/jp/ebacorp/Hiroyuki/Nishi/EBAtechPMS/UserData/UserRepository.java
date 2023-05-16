package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.UserData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//User(会員)のリポジトリ。DBのテーブルをJavaのインターフェースにしたようなもの
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
