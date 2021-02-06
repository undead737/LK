package ru.ofd.lk.security.auth.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.ofd.lk.security.models.lkAuth.AuthRole;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    private final UserDetailsService detailsService;

    @Autowired
    public JwtTokenProvider(@Qualifier("applicationUserDetailsService") UserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    /**
     * Generated token based on: userName, roles, token_secret (from application.properties) and lifetime (24H)
     * @param userName user login
     * @param role user roles
     * @return assembled token
     */
    public String generateToken(String userName, List<AuthRole> role){
        Claims cl = Jwts.claims().setSubject(userName);
        cl.put("roles", role.stream().map(AuthRole::getRoleName).collect(Collectors.toList()));
        Date now = new Date(System.currentTimeMillis());
        long time = 3600000;
        Date validityDate = new Date(now.getTime() + time);
        return Jwts.builder().setClaims(cl).setIssuedAt(now).setExpiration(validityDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token){
        UserDetails uDetails = detailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(uDetails, "", uDetails.getAuthorities());
    }

    /**
     *
     * @param req
     * @return
     */
    public String resolveToken(HttpServletRequest req){
        String token = req.getHeader("Authorization");
        if (token!=null && token.startsWith("Bearer")){
            return token.substring(7);
        }
        return null;
    }

    /**
     * receive login from token
     */
    private String getUserName(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     *
     */
    public boolean isValid(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException ex) {
            throw new JwtAuthException("");
        }
    }
}
