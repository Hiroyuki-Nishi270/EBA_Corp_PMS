package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.UserData;

import org.springframework.data.repository.CrudRepository;

//User(会員)のリポジトリ。DBのテーブルをJavaのインターフェースにしたようなもの
public interface UserRepository extends CrudRepository<User,Long> {
}
