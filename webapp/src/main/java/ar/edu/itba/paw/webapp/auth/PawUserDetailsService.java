package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.interfaces.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class PawUserDetailsService  implements UserDetailsService {


    @Autowired
    private UserService us;

    @Override //El username es lo que le dijimos que es la clase principal (email)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final ar.edu.itba.paw.models.User user = us.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No user for " + username));

        final Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if(user.isEnabled()) {
            if(user.getIsProfessor()){
                authorities.add(new SimpleGrantedAuthority("ROLE_EDITOR"));
            }
            authorities.add(new SimpleGrantedAuthority("ROLE_READER"));
        }

//        if ers professor le argego rol etc. o si tiene x puntos le doy permisos cosas asi.
//        es una buena idea que los roles no esten asociados a los perfiles de usuarios porque escala mucho mejor
        return new User(username,user.getPassword(),authorities);
    }
}
