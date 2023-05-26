package jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS;


import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class inputForm {

    @Pattern(regexp = "[0-9a-zA-Z]{1,32}")
    private String username;

    @Pattern(regexp = "(?=.*[A-Z])(?=.*[.?/-])(?=.*[a-z])[a-zA-Z0-9.?/-]{8,24}")
    private String password1;

    @Pattern(regexp = "(?=.*[A-Z])(?=.*[.?/-])(?=.*[a-z])[a-zA-Z0-9.?/-]{8,24}")
    private String password2;
}
