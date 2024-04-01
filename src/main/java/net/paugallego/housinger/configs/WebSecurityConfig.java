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
                    .antMatchers(HttpMethod.POST, "/v1/auth/sign-up").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/auth/sign-in").permitAll()
                    .antMatchers(HttpMethod.GET, "/v1/bedType/get/**").hasRole("A")
                    .antMatchers(HttpMethod.PUT, "v1/calendar/**").hasAnyRole("A", "B", "C")
                    .anyRequest().authenticated()
            .and()
                .addFilter(new UsernameAndPasswordAuthenticationFilterImplementation(authenticationManagerBean()))
                .addFilterBefore(OPRFImp, BasicAuthenticationFilter.class)
                .formLogin().disable();
                

//                .antMatchers(HttpMethod.GET, "/friend/*").hasRole("USER")
//                .antMatchers(HttpMethod.PUT, "/friend/*").hasRole("USER")
//                .antMatchers(HttpMethod.DELETE, "/friend/*").hasRole("USER")
//
//
//                .antMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
//                .antMatchers(HttpMethod.POST, "/auth/sign-up/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/auth/sign-in").permitAll()
//                .antMatchers(HttpMethod.PUT, "/auth/activate").permitAll()
//
//                .antMatchers(HttpMethod.GET, "/author/*").permitAll()
//                .antMatchers(HttpMethod.PUT, "/author/follow/*").hasRole("USER")
//                .antMatchers(HttpMethod.PUT, "/author/change-personal-data").hasRole("AUTHOR")
//
//                .antMatchers(HttpMethod.GET, "/user/*").permitAll()
//                .antMatchers(HttpMethod.GET, "/user/me").hasRole("USER")
//                .antMatchers(HttpMethod.GET, "/user/recommendations").hasRole("USER")
//
//                .antMatchers(HttpMethod.GET, "/book/*").permitAll()
//                .antMatchers(HttpMethod.POST, "/book/*").hasAnyRole("AUTHOR", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/book/approval").authenticated()
//                .antMatchers(HttpMethod.PUT, "/*").hasRole("USER")
//                .antMatchers(HttpMethod.DELETE, "/*").hasRole("ADMIN")
//
//                .antMatchers(HttpMethod.GET, "/saga/*").permitAll()
//                .antMatchers(HttpMethod.POST, "/saga").hasAnyRole("AUTHOR", "ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/saga/*").hasAnyRole("AUTHOR", "ADMIN")
//
//                .antMatchers(HttpMethod.GET, "/statistics/user/*").hasRole("USER")
//                .antMatchers(HttpMethod.GET, "/statistics/author/*").hasRole("AUTHOR")
//                .antMatchers(HttpMethod.GET, "/statistics/admin").hasRole("ADMIN")


    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServ).passwordEncoder(passwordEncoder);
    }
}
