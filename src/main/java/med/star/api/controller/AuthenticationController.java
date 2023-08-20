package med.star.api.controller;

import jakarta.validation.Valid;
import med.star.api.domain.users.AuthenticationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity Authenticate(@RequestBody AuthenticationData authenticationData){
        Authentication token = new UsernamePasswordAuthenticationToken(authenticationData.username(), authenticationData.pass());
        System.out.println(token.toString());
        authManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
