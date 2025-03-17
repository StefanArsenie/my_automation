package tests;
import api_pom.dto.authentication.AuthenticationTokenResponseDTO;
import helper.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.AuthenticationRequest;
import utils.Constants;

class AuthenticationWithValidTokenReturnedTest {
    AuthenticationRequest authenticationRequest = new AuthenticationRequest();
    @Test
    void authenticateShouldReturnValidTokenResponse() {
        Helper helper = new Helper();

        Object response =
                authenticationRequest.authenticate(Constants.CLIENT_NAME, helper.emailGeneration());

        if (response instanceof AuthenticationTokenResponseDTO tokenResponse) {
            Assertions.assertEquals(64, tokenResponse.getAccessToken().length(),
                    "Token has expected length");
            Assertions.assertEquals(201, authenticationRequest.getStatusCode());
        } else {
            Assertions.fail("Expected a successful token but received an error");
        }
    }
}
