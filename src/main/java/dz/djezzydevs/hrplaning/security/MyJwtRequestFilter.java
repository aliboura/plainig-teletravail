package dz.djezzydevs.hrplaning.security;



import dz.djezzydevs.hrplaning.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyJwtRequestFilter  extends OncePerRequestFilter {

//
//    @Autowired
//    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        JwtUtil jwtUtil =new JwtUtil();

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
            String a="5";

        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           //   UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            //  UserDetails userDetails = new User(username,"s",new ArrayList<>());

            if (jwtUtil.validateToken(jwt, username)) {

                Claims claims=jwtUtil.validateTokenClaims(jwt);

                setUpSpringAuthentication(claims);
                // what spring should do automatically  in default cases
            }
        }
        chain.doFilter(request, response); //I've done my job as filter -> continue

    }


    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List) claims.get("claims");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }
}
