package cn.joey.mvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class ItemsValidator implements Validator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z]\\w{4,19}");
    private static final Pattern PIC_PATTERN = Pattern.compile("[a-zA-Z0-9]{5,20}");

    private static final Set<String> FORBINDDEN_WORK_SET = new HashSet<String>();

    static{
        FORBINDDEN_WORK_SET.add("fuck");
        FORBINDDEN_WORK_SET.add("admin");
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Items.class ==clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"name","name.not.empty");

        Items items = (Items)target;
        if (!NAME_PATTERN.matcher(items.getName()).matches()) {
            errors.rejectValue("name","name.not.illegal");
        }
        for(String forbidden:FORBINDDEN_WORK_SET){
            if(items.getName().contains(forbidden)){
                errors.rejectValue("name","name.forbidden",new Object[]{forbidden},"您的用户名中包含非法关键字");
            }
        }

        if (!PIC_PATTERN.matcher(items.getPic()).matches()) {
            errors.rejectValue("pic","pic.not.illegal","密码不合法");
        }
    }
}
