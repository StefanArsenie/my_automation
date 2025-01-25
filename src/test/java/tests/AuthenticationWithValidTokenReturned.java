package tests;

import api_pom.dto.authentication.AuthenticationTokenResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.AuthenticationRequest;
import utils.Constants;

public class AuthenticationWithValidTokenReturned {

    @Test
    public void authenticateShouldReturnValidTokenResponse() {
        String clientName = Constants.CLIENT_NAME;
        String clientEmail = Constants.CLIENT_EMAIL;

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        AuthenticationTokenResponseDTO tokenResponse = authenticationRequest.authenticate(clientName, clientEmail);

        Assertions.assertEquals(64, tokenResponse.getAccessToken().length(), "Token has expected length");
    }
}
