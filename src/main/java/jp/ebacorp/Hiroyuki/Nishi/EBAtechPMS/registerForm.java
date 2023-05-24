package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class registerForm {

    @Pattern(regexp = "[0-9a-zA-Z]{1,32}", message = "ユーザー名には１文字以上３２文字以内の英数字のみを使用してください")
    private String username;

    @Pattern(regexp = "(?=.*[A-Z])(?=.*[.?/-])(?=.*[a-z])[a-zA-Z0-9.?/-]{8,24}", message = "passwordには８文字以上２４文字以内の英数字と記号のみを使用してください")
    private String password1;

    private String password2;
}
