package ar.edu.itba.paw.webapp.forms.validations.image.size;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = imageSizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxImageSize {
    String message() default "La imagen es muy grande";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
