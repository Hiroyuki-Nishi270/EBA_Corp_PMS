package jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class userRegisterForm {

    @Pattern(regexp = "[0-9a-zA-Z]{1,32}")
    private String username;

    @Pattern(regexp = "(?=.*[A-Z])(?=.*[.?/-])(?=.*[a-z])[a-zA-Z0-9.?/-]{8,24}")
    private String password1;

    private String password2;

    @AssertTrue(message = "入力された２つのパスワードが一致しておりません。")
    public boolean isSamePassword(){
        if(this.password1.equals(this.password2)){
            return true;
        }else{
            return false;
        }
    }
}
