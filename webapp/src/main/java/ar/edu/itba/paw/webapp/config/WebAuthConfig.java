package ar.edu.itba.paw.webapp.config;

import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.auth.PawUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {

    public static final Logger LOGGER
            = LoggerFactory.getLogger(WebAuthConfig.class);

    @Autowired
    private PawUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserService userService;

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                Authentication auth
                        = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null ) {

                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String username;
                    if (principal instanceof UserDetails) {
                        username = ((UserDetails) principal).getUsername();
                    } else {
                        username = principal.toString();
                    }
                    Optional<User> user = userService.findByEmail(username);
                    LOGGER.warn("User: " + auth.getName()
                            + " attempted to access the protected URL: "
                            + httpServletRequest.getRequestURI());
                    if(user.isPresent() && !user.get().isEnabled()){
                        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/verify");
                        return;
                    }
                }
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/error403");
            }
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.sessionManagement()
                .invalidSessionUrl("/login") //Cuando hay una sesion invalida al usuario lo mandamos al /login
                .and().formLogin() //Los post que hagamos a /login los intercepta springsecurity, los get no :/ continuara en controller
                    .defaultSuccessUrl("/home",false)
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                .and().rememberMe()
                    .key(getKey(Charset.defaultCharset()))
                    .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(30))
                    .userDetailsService(userDetailsService)
                    .rememberMeParameter("rememberme")
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/") //Si se desloginea exitosamente lo mando al home
                .and().authorizeRequests()
                    .antMatchers("/register","/login","/registerProfessor","/registerStudent").anonymous() //Para meterse en esos dos necesitan estar deslogeados
                    .antMatchers("/studentProfile","/student/**","/myLessons/**","/class/**").hasRole("READER")
                    .antMatchers("/professorProfile","/myStudents/**","/materias/**").hasRole("EDITOR")
                    .antMatchers("/contactar").hasRole("EDITOR") //Que nos contacten solo los profesores
                    .antMatchers("/contactarProfesor","/nuevaMateria").authenticated()
                    .antMatchers(HttpMethod.POST,"/verify/send").authenticated()
                .and().exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler())
                .and().csrf().disable();
        //CUIDADO: LA PRIMER REGLA QUE MATCHEA DEFINE, SI UNA REGLA MATCHEA CONRTA CUALQUIER COSA, OBVIO QUE VA ULTIMA
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/script**","/resources/**","/styles/**","/js/**","/images/**","/favicon.ico","/errors/**");
        //Apago spring security para los assets publicos
    }

    private static String getKey(Charset encoding) throws IOException, URISyntaxException {
        URL certificate = WebAuthConfig.class.getClassLoader().getResource("SSLcertificate.key");
        if(certificate == null)
            throw new RuntimeException();
        byte[] encoded = Files.readAllBytes(Paths.get(certificate.toURI()));
        return new String(encoded,encoding);
    }

}