package net.paugallego.housinger.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import net.paugallego.housinger.configs.SecurityConstants;
import net.paugallego.housinger.model.database.entities.RoleEnum;
import net.paugallego.housinger.model.database.entities.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JWTTokenProvider {

    private static SecretKey key;

    public static String generateToken(Authentication auth) {
        UserEntity user = (UserEntity) auth.getPrincipal();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.TYPE)
                .setSubject(user.getMail())
                .setId(user.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .claim("roles", user.getRoles().stream().map(RoleEnum::name).collect(Collectors.joining(", ")))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public static boolean validateToken(String token) throws Exception {
        boolean valid = false;

        try {
            Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
            valid = true;
        } catch (SignatureException ex) {
            System.out.println("Signature token is not valid.");
            throw new Exception();
        } catch (MalformedJwtException ex) {
            System.out.println("El token te lo has sacao de la manga.");
            throw new Exception();
        } catch (ExpiredJwtException ex) {
            System.out.println("El token se jodio hace meses bru.");
            throw new Exception();
        } catch (UnsupportedJwtException ex) {
            System.out.println("Te has equivocao de tipo de token, imbecil.");
            throw new Exception();
        } catch (IllegalArgumentException ex) {
            System.out.println("El \"Exception\" sin poner exception, que listos jaja.");
            throw new Exception();
        }

        return valid;
    }


    public static Long getIdByToken(String token) {
        return Long.valueOf((String) Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody().get("jti"));
    }


    private static SecretKey getKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(SecurityConstants.SECRET.getBytes());
        }
        return key;
    }

}
