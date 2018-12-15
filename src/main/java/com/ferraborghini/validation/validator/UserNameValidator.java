package com.ferraborghini.validation.validator;

import com.alibaba.fastjson.JSONObject;
import com.ferraborghini.error.ErrorEntity;
import com.ferraborghini.error.ServiceError;
import com.ferraborghini.validation.validate.ValidatorUserName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<ValidatorUserName, String> {
    @Override
    public void initialize(ValidatorUserName validatorUserName) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        int strLen = s.length();
        if (strLen == 0) {
            ErrorEntity error = ServiceError.JSON_FORMAT_ERROR.responseEntity("name is empty");
            constraintValidatorContext.buildConstraintViolationWithTemplate(JSONObject.toJSONString(error))
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }
        return true;
    }
}
