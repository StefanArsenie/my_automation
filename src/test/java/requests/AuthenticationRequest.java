package requests;

import api_pom.dto.authentication.AuthenticationErrorResponseDTO;
import api_pom.dto.authentication.AuthenticationRequestDTO;
import api_pom.dto.authentication.AuthenticationTokenResponseDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import utils.UrlResources;

public class AuthenticationRequest {

    AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();

    @Getter
    private Response lastResponse;
    public Object authenticate(String clientName, String clientEmail) {
        requestDTO.setClientName(clientName);
        requestDTO.setClientEmail(clientEmail);

         lastResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri(UrlResources.BASE_URI.getUrl())
                .basePath(UrlResources.AUTH_ENDPOINT.getUrl())
                .body(requestDTO).log().all()
                 .when()
                .post()
                .then()
                .log().all()
                .extract().response();

        int statusCode = lastResponse.getStatusCode();

        if(statusCode == 201) {
            return lastResponse.as(AuthenticationTokenResponseDTO.class);

        } else {
            return lastResponse.as(AuthenticationErrorResponseDTO.class);
        }
    }
    public int getStatusCode() {
        return lastResponse.getStatusCode();
    }
}
