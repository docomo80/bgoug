package com.example.bgoug.validator;

import com.example.bgoug.annotation.IsPasswordMatching;
import com.example.bgoug.member.entities.Member;
import com.example.bgoug.member.models.bindingModels.MemberModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching, Object> {

    @Override
    public void initialize(IsPasswordMatching constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object memberClass, ConstraintValidatorContext constraintValidatorContext) {
        if (memberClass instanceof MemberModel) {
            return ((MemberModel) memberClass).getPassword()
                    .equals(((MemberModel) memberClass).getConformedPassword());
        }

        return false;
    }
}
