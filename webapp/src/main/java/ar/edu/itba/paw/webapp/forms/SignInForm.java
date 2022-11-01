package ar.edu.itba.paw.webapp.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class SignInForm
{
    @NotEmpty()@Email()
    private String email;

    @NotEmpty()@Size(min = 6, max = 30)
    private String contrasena;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String usuario)
    {
        this.email = email;
    }

    public String getContrasena()
    {
        return contrasena;
    }

    public void setContrasena(String contrasena)
    {
        this.contrasena = contrasena;
    }


}
