package ru.ofd.lk.security.auth.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider provider;

    private static String jwt = "";

    public JwtTokenFilter(JwtTokenProvider provider) {
        this.provider = provider;
    }

    /**
     * simulation jwt-token in request
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token;
        if (!jwt.equals("")) token = jwt;
        else token = provider.resolveToken((HttpServletRequest) servletRequest);
        if (token!=null && provider.isValid(token)){
            Authentication auth = provider.getAuthentication(token);
            if (auth!=null) SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //simulation
    public static void login(String jwtt){
        jwt = jwtt;
    }

    //simulation
    public static void logout(){
        jwt = "";
    }
}
