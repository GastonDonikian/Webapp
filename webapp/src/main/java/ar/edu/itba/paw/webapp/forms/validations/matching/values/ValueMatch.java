package ar.edu.itba.paw.webapp.forms.validations.matching.values;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;



@Constraint(validatedBy = valueMatchValidator.class)
@Target({ ElementType.TYPE,ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValueMatch {
    String message() default "Los valores no coinciden";
    String field();
    String fieldMatch();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
