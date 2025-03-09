package requests;

import api_pom.dto.getBookById.GetBookByIdResponseDTO;
import api_pom.dto.orderBook.SubmitOrderBookResponseDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import utils.UrlResources;

@Log4j2
public class GetBookByIdRequest {
    private final String token;

    @Getter
    private Response response;

    public GetBookByIdRequest() {
        this.token = TokenManager.accessToken();
    }

    public GetBookByIdResponseDTO getBookById(String orderId) {

        response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .baseUri(UrlResources.BASE_URI.getUrl())
                .basePath(UrlResources.ADD_BOOK_ORDER_ENDPOINT.getUrl())
                .when()
                .get(orderId)
                .then().log().all()
                .extract().response();

        return response.as(GetBookByIdResponseDTO.class);
    }
    public int getStatusCode() {
        if(response == null) {
            throw new IllegalArgumentException("Response is null");
        }
        else {
            return response.getStatusCode();
        }
    }
}
