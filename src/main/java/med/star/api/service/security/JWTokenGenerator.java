package med.star.api.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.star.api.domain.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTokenGenerator {
    @Value("${project.secret.key}")
    private String SecretKey;
    public String generateToken(User user) {
        String jwtoken = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SecretKey);
            jwtoken = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }

        return jwtoken;
    }

    //To generate expDate
    private Instant generateExpDate(){
        ZoneOffset offset = ZoneOffset.ofHoursMinutes(5, 0);
        return LocalDateTime.now().plusHours(1).toInstant(offset);
    }

}
