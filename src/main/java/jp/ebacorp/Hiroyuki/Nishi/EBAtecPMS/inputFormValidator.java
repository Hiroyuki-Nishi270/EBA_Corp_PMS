package jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class inputFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return inputForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        inputForm InputForm = (inputForm) target;

        //値が
        if ((inputForm.getPassword1().isEmpty() != true)
                && (inputForm.getPassword2().isEmpty() != true)
                && (inputForm.getPassword1().equals(InputForm.getPassword2()) != true)){
            System.out.println("パスワード不一致");
            errors.reject("jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS.registerFormValidator.message");
        }
    }
}
