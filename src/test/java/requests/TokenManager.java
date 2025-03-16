package requests;

import api_pom.dto.authentication.AuthenticationRequestDTO;
import api_pom.dto.authentication.AuthenticationTokenResponseDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import utils.Constants;
import utils.UrlResources;

@Getter @Setter

public class TokenManager {

    public static String myToken;

    public static String accessToken() {
        if(myToken == null) {
            authenticateAndSetToken();
        }
        return myToken;
    }
    private static void authenticateAndSetToken() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();
        requestDTO.setClientName(Constants.CLIENT_NAME);
        requestDTO.setClientEmail(Constants.);

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri(UrlResources.BASE_URI.getUrl())
                .basePath(UrlResources.AUTH_ENDPOINT.getUrl())
                .body(requestDTO)
                .post()
                .then()
                .extract().response();

        if(response.getStatusCode() == 201) {
            AuthenticationTokenResponseDTO auhtRequest = response.as(AuthenticationTokenResponseDTO.class);
            myToken = auhtRequest.getAccessToken();
        } else {
            throw new RuntimeException("Authentication failed: " + response.getStatusCode());
        }
    }
}
