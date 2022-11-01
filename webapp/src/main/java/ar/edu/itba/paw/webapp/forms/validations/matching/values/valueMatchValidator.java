package ar.edu.itba.paw.webapp.forms.validations.matching.values;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class valueMatchValidator implements ConstraintValidator<ValueMatch, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(ValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldRepeatValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        if(fieldValue != null)
            return fieldValue.equals(fieldRepeatValue);
        return fieldRepeatValue == null;
    }
}
