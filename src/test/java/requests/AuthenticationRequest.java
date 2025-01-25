package requests;

import api_pom.dto.authentication.AuthenticationRequestDTO;
import api_pom.dto.authentication.AuthenticationTokenResponseDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Constants;
import utils.UrlResources;

public class AuthenticationRequest {

    public AuthenticationTokenResponseDTO authenticate(String clientName, String clientEmail) {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();
        requestDTO.setClientName(clientName);
        requestDTO.setClientEmail(clientEmail);

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri(UrlResources.BASE_URI.getUrl())
                .basePath(UrlResources.AUTH_ENDPOINT.getUrl())
                .body(requestDTO).log().all()
                .post()
                .then().log().all()
                .extract().response();

        return response.as(AuthenticationTokenResponseDTO.class);

    }
}
