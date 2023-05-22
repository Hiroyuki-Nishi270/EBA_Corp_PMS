package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class registerForm {

    @NotNull(message = "名前が入っておりません")
    @Length(min = 1, max = 32, message = "名前の長さが不適切です。３２文字以内に納めてください")
    private String username;

    @NotNull(message = "パスワードが空欄です")
    @Length(min = 1, max = 32, message = "３２文字以内に納めてください")
    private String password1;

    @NotNull(message = "パスワードが空欄です")
    @Length(min = 1, max = 32, message = "３２文字以内に納めてください")
    private String password2;
}
