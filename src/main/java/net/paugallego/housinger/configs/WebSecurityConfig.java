package net.paugallego.housinger.configs;

import net.paugallego.housinger.security.OncePerRequestFilterImplementation;
import net.paugallego.housinger.security.UsernameAndPasswordAuthenticationFilterImplementation;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //filtros
    @Autowired
    private OncePerRequestFilterImplementation OPRFImp;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCRUDService userServ;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/auth/**").permitAll()
                    .antMatchers("/v1/recover/**").permitAll()
                    .antMatchers("/ws/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/fileCustomer/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/propertyCharacteristics/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/propertyCalendar/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/propertyLocation/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/v1/user", "/v1/user/**").hasAnyRole("A", "U")
                    .antMatchers(HttpMethod.POST,  "/v1/user", "/v1/user/**").hasRole("A")
                    .antMatchers(HttpMethod.DELETE, "/v1/user", "/v1/user/**").hasRole("A")
                    .antMatchers(HttpMethod.PUT, "/v1/user").hasAnyRole("A", "U")
                    .antMatchers(HttpMethod.GET, "/v1/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/**").hasAnyRole("A", "U")
                    .antMatchers(HttpMethod.PUT, "/v1/**").hasAnyRole("A", "U")
                    .antMatchers(HttpMethod.DELETE, "/v1/**").hasAnyRole("A", "U")
                    .anyRequest().authenticated()
            .and()
                .addFilter(new UsernameAndPasswordAuthenticationFilterImplementation(authenticationManagerBean()))
                .addFilterBefore(OPRFImp, BasicAuthenticationFilter.class)
                .formLogin().disable();
                

//                .antMatchers(HttpMethod.PUT, "/auth/activate").permitAll()

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServ).passwordEncoder(passwordEncoder);
    }
}
