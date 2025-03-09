package tests;
import api_pom.dto.authentication.AuthenticationTokenResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.AuthenticationRequest;
import utils.Constants;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class AuthenticationWithValidTokenReturnedTest {
    String clientName = Constants.CLIENT_NAME;
    String clientEmail = Constants.CLIENT_EMAIL;
    AuthenticationRequest authenticationRequest = new AuthenticationRequest();
    @Test
    void authenticateShouldReturnValidTokenResponse() {

        Object response =
                authenticationRequest.authenticate(clientName, clientEmail);

        assertThat(201, equalTo(authenticationRequest.getStatusCode()));

        if (response instanceof AuthenticationTokenResponseDTO tokenResponse) {
            Assertions.assertEquals(64, tokenResponse.getAccessToken().length(),
                    "Token has expected length");
        } else {
            Assertions.fail("Expected a successful token but received an error");
        }
    }
}
