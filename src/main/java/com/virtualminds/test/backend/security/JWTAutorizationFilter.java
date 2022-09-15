package com.virtualminds.test.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAutorizationFilter extends OncePerRequestFilter {


    //check for token validity
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Expose-Headers", "*");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Request-Methods","*");
        response.addHeader("Access-Control-Allow-Methods", "*");



        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
            if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();
            String username = claims.getSubject();
            ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
            Collection<GrantedAuthority> authoroties = new ArrayList<>();
            roles.forEach(role -> {
                authoroties.add(new SimpleGrantedAuthority(role.get("authority")));
            });

            UsernamePasswordAuthenticationToken
                        authenticatedUser = new UsernamePasswordAuthenticationToken(username, null, authoroties);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            filterChain.doFilter(request, response);
        }


    }

}

