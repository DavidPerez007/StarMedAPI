package med.star.api.service.security;
// This record is needed for the response at AuthenticationController to return a JWT in JSON format instead of raw
public record JWTData(String jwtoken) {

}
