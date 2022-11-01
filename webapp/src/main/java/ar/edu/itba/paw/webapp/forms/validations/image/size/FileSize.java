package ar.edu.itba.paw.webapp.forms.validations.image.size;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = fileSizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {
    String message() default "El archivo no existe o es muy grande";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
