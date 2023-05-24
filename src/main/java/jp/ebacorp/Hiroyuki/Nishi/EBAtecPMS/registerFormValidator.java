package jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class registerFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return registerForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        registerForm registerForm = (registerForm) target;

        //値が
        if ((registerForm.getPassword1().isEmpty() != true)
                && (registerForm.getPassword2().isEmpty() != true)
                && (registerForm.getPassword1().equals(registerForm.getPassword2()) != true)){
            System.out.println("パスワード不一致");
            errors.reject("jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS.registerFormValidator.message");
        }
    }
}
