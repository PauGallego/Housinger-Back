package net.paugallego.housinger.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import net.paugallego.housinger.configs.SecurityConstants;
import net.paugallego.housinger.model.database.entities.UserEntity;
import net.paugallego.housinger.services.crud.entity.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter
public class OncePerRequestFilterImplementation extends OncePerRequestFilter {


    @Autowired
    UserCRUDService userServ;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String header = httpServletRequest.getHeader(SecurityConstants.HEADER);

        if (header == null || !header.startsWith("Authentication " + SecurityConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);

            return;

        }

        UsernamePasswordAuthenticationToken authentication;

        try {
            authentication = getAuthentication(httpServletRequest);
            authentication.setDetails(new WebAuthenticationDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            System.out.println("sus muertos");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws Exception {
        String token = request.getHeader(SecurityConstants.HEADER).replace("Authentication " + SecurityConstants.TOKEN_PREFIX, "");
        UsernamePasswordAuthenticationToken upat = null;

        if (token != null && !token.isEmpty() && JWTTokenProvider.validateToken(token)) {
            Long idUser = JWTTokenProvider.getIdByToken(token);
            UserEntity user = (UserEntity) userServ.loadUserById(idUser);

            upat = new UsernamePasswordAuthenticationToken(idUser, user.getRoles(), user.getAuthorities());
        }

        return upat;
    }
}
