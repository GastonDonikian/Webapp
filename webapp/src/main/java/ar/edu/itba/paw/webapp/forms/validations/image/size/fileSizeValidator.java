package ar.edu.itba.paw.webapp.forms.validations.image.size;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class fileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    @Override
    public void initialize(FileSize fileSize){}

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty() && value.getSize() <= 10000000;
    }
}
