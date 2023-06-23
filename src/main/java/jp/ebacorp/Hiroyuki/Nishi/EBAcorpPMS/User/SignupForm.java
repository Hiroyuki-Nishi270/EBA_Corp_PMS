package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.User;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class SignupForm {

    @Pattern(regexp = "[0-9A-Z]{1,32}", message ="ユーザー名は半角英数字かつ大文字で３２文字以内で設定してください")
    private String username;

    @Pattern(regexp = "(?=.*[A-Z])(?=.*[.?/-])(?=.*[a-z])[a-zA-Z0-9.?/-]{8,24}",
            message ="パスワードは８文字以上２４文字以内、最低１文字の大文字と小文字、記号(.?/-を入れてください)")
    private String password1;

    @NotBlank
    private String password2;

    @AssertTrue(message = "入力された２つのパスワードが一致しておりません。")
    public boolean isPasswordValid(){
        if(this.password1.equals(this.password2)){
            return true;
        }else{
            return false;
        }
    }

}
