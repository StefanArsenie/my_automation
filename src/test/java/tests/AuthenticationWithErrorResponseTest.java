package tests;

import api_pom.dto.authentication.AuthenticationErrorResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.AuthenticationRequest;
import utils.Constants;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class AuthenticationWithErrorResponseTest {
    String clientName = Constants.CLIENT_NAME;


    @Test
    void authenticationShouldReturn409ConflictError() {

        String clientEmail = Constants.FIXED_EMAIL;
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        Object response = authenticationRequest.authenticate(clientName, clientEmail);

        assertThat(409, equalTo(authenticationRequest.getStatusCode()));

        if(response instanceof AuthenticationErrorResponseDTO errorResponse) {
            assertThat("API client already registered. Try a different email." , equalTo(errorResponse.getError()));
        } else {
            Assertions.fail("Expected an error but received a success token response");
        }
    }
    @Test
    void authenticationEmailWithoutComWordReturn400BadRequestError() {
        String clientEmail = Constants.DEFORMED_EMAIL_1;
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        Object response = authenticationRequest.authenticate(clientName, clientEmail);

        assertThat(400, equalTo(authenticationRequest.getStatusCode()));

        if (response instanceof AuthenticationErrorResponseDTO errorResponse) {
            assertThat("Invalid or missing client email.", equalTo(errorResponse.getError()));
        } else {
            Assertions.fail("Expected an error but received a success token response");
        }
    }
    @Test
    void authenticationEmailWithoutAtSign400BadRequestError() {
        String clientEmail = Constants.DEFORMED_EMAIL_2;
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        Object response = authenticationRequest.authenticate(clientName, clientEmail);

        assertThat(400, equalTo(authenticationRequest.getStatusCode()));

        if (response instanceof AuthenticationErrorResponseDTO errorResponse) {
            assertThat("Invalid or missing client email.", equalTo(errorResponse.getError()));
        } else {
            Assertions.fail("Expected an error but received a success token response");
        }
    }
    @Test
    void authenticationEmailStartingWithAtSignReturn400BadRequestError() {
        String clientEmail = Constants.DEFORMED_EMAIL_3;
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        Object response = authenticationRequest.authenticate(clientName, clientEmail);

        assertThat(400, equalTo(authenticationRequest.getStatusCode()));

        if (response instanceof AuthenticationErrorResponseDTO) {
            AuthenticationErrorResponseDTO errorResponse = (AuthenticationErrorResponseDTO) response;
            assertThat("Invalid or missing client email.", equalTo(errorResponse.getError()));
        } else {
            Assertions.fail("Expected an error but received a success token response");
        }
    }
}
