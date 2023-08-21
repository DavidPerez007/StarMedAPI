package med.star.api.service.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.star.api.domain.users.User;
import med.star.api.repository.UserRepository;
import med.star.api.service.security.JWTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JWTokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var reqHeader = request.getHeader("Authorization");
        if(reqHeader != null){
            var token = reqHeader.replace("Bearer ", ""); //So the string returned has no "Bearer " word at the beginning, yep, more than 1 hour to realize that after Bearer, it exists a blank space that didn't let the token be valid
            System.out.println("token is: " + token);
            var subject = this.tokenService.getTokenSubject(token);
            System.out.println("subject is: " + subject);
            System.out.println("subject is: " + subject);
            if(subject != null){
                UserDetails user = this.userRepository.findByUsername(subject);
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request, response);




    }
}
