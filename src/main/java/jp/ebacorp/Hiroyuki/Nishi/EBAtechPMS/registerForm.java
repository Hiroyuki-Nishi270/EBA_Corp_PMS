package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class registerForm {

    //@NotNull(message = "名前が入っておりません")
    @Length(min = 1, max = 32, message = "usernameを１文字以上３２文字以内に納めてください")
    private String username;

    //@NotNull(message = "パスワードが空欄です")
    @Length(min = 1, max = 32, message = "password1を１文字以上３２文字以内に納めてください")
    private String password1;

    //@NotNull(message = "パスワードが空欄です")
    @Length(min = 1, max = 32, message = "passoword2を１文字以上３２文字以内に納めてください")
    private String password2;
}
