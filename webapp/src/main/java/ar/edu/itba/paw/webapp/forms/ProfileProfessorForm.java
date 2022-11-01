package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.webapp.forms.validations.image.size.MaxImageSize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProfileProfessorForm
{
    @NotEmpty()@Size(min = 3, max = 20)
    private String name;

    @NotEmpty()@Size(min = 3, max = 20)
    private String surname;

    @NotEmpty()@Email()
    private String email;

    @NotEmpty()@Size(min = 8, max = 15)@Pattern(regexp = "[0-9]+")
    private String phone;

    @Max(120)
    private Integer age;

    @NotEmpty()@Size(max = 30)
    private String studies;

    @Size(max = 30)
    private String schedule;

    @Size(max = 200)
    private String description;

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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
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

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public String getStudies()
    {
        return studies;
    }

    public void setStudies(String studies)
    {
        this.studies = studies;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
