package net.paugallego.housinger.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.paugallego.housinger.configs.SecurityConstants;
import net.paugallego.housinger.model.dto.LoginDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class UsernameAndPasswordAuthenticationFilterImplementation extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    public UsernameAndPasswordAuthenticationFilterImplementation(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(SecurityConstants.SIGN_IN_ENDPOINT);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginDTO basura = null;
        try {
            basura = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
            System.out.println("Parsed Basura: " + basura);  // Log here
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("JsonProcessingException when parsing Basura: " + e.getMessage());  // Log here
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Null check for basura
        if(basura == null) {
            throw new RuntimeException("Invalid request format. Please check the payload");
        }

        //Null check for authenticationManager
        if(authenticationManager == null) {
            throw new RuntimeException("AuthenticationManager is not initialized. Please check the configuration");
        }

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(basura.getUsername(), basura.getPassword()));

        System.out.println("Authentication successful: " + auth); // Log here

        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWTTokenProvider.generateToken(authResult);

        System.out.println(token);

        response.addHeader(SecurityConstants.HEADER, SecurityConstants.TOKEN_PREFIX + token);
    }
}
