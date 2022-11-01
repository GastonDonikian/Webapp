package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.webapp.forms.validations.image.size.FileSize;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

public class UploadFileForm {

    @NotEmpty()@Size(min = 1, max = 30)
    private String name;

    @FileSize
    private MultipartFile file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
