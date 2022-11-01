package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.webapp.forms.validations.image.size.MaxImageSize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProfileStudentForm
{
    @NotEmpty()@Size(min = 3, max = 20)
    private String name;

    @NotEmpty()@Size(min = 3, max = 20)
    private String surname;

    @NotEmpty()@Email()
    private String email;

    @NotEmpty()@Size(min = 8, max = 10)@Pattern(regexp = "[0-9]+")
    private String phone;

    @MaxImageSize
    private MultipartFile profileImage;

    public MultipartFile getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartFile profileImage) {
        this.profileImage = profileImage;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

}
