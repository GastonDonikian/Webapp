package ar.edu.itba.paw.webapp.forms.validations.image.size;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class imageSizeValidator implements ConstraintValidator<MaxImageSize, MultipartFile> {

    @Override
    public void initialize(MaxImageSize maxImageSize){}

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value.getSize() <= 1000000;
    }
}
