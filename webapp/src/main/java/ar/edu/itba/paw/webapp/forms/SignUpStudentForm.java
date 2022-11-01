package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.webapp.forms.validations.matching.values.ValueMatch;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

@ValueMatch(field = "password", fieldMatch = "repeatPassword",message = "Las contrasenas no coinciden")
public class SignUpStudentForm
{
    @NotEmpty()@Size(min = 3, max = 20)
    private String name;

    @NotEmpty()@Size(min = 3, max = 20)
    private String surname;



    @NotEmpty()@Email()
    private String email;

    @NotEmpty()@Size(min = 6, max = 30)
    private String password;

    @NotEmpty()@Size(min = 6, max = 30)
    private String repeatPassword;

    @NotEmpty()@Size(min = 8, max = 10)@Pattern(regexp = "[0-9]+")
    private String phone;

    @Max(120)
    private Integer age;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
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

}
