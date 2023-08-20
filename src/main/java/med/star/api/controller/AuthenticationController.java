package med.star.api.controller;

import med.star.api.domain.users.AuthenticationData;
import med.star.api.domain.users.User;
import med.star.api.repository.UserRepository;
import med.star.api.service.security.JWTData;
import med.star.api.service.security.JWTokenGenerator;
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
    @Autowired
    private JWTokenGenerator tokenGenerator;

    @PostMapping
    public ResponseEntity Authenticate(@RequestBody AuthenticationData authenticationData){
        Authentication token = new UsernamePasswordAuthenticationToken(authenticationData.username(), authenticationData.pass());
        var authUser = authManager.authenticate(token);
        var jwtoken = tokenGenerator.generateToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JWTData(jwtoken));
    }
}
