package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS;


import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class registerForm {

    @Pattern(regexp = "[0-9a-zA-Z]{1,32}")
    private String username;

    @Pattern(regexp = "(?=.*[A-Z])(?=.*[.?/-])(?=.*[a-z])[a-zA-Z0-9.?/-]{8,24}")
    private String password1;

    private String password2;
}
