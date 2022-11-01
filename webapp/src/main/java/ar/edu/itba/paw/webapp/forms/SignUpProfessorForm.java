package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.webapp.forms.validations.matching.values.ValueMatch;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ValueMatch(field = "password", fieldMatch = "repeatPassword",message = "Las contrasenas no coinciden")
public class SignUpProfessorForm
{

    @NotEmpty()@Size(min = 3, max = 20)
    private String name;

    @NotEmpty()@Size(min = 3, max = 20)
    private String surname;

    @NotEmpty()@Email()@Size(max = 30)
    private String email;

    @NotEmpty()@Size(min = 6, max = 30)
    private String password;

    @NotEmpty()@Size(min = 6, max = 30)
    private String repeatPassword;

    private Professor.Location location;


    public Professor.Location getLocation() {
        return location;
    }

    public void setLocation(Professor.Location location) {
        this.location = location;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @NotEmpty()@Size(min = 8, max = 10)@Pattern(regexp = "[0-9]+")
    private String phone;

    @Max(120)
    private Integer age;

    @NotEmpty()@Size(min = 3, max = 30)
    private String studies;

    @Size(max = 200)
    private String description;

    @Size(max = 30)
    private String schedule;

    public String getSchedule()
    {
        return schedule;
    }

    public void setSchedule(String schedule)
    {
        this.schedule = schedule;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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
