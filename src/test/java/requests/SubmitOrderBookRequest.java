package requests;

import api_pom.dto.authentication.AuthenticationTokenResponseDTO;
import api_pom.dto.orderBook.SubmitOrderBookRequestDTO;
import api_pom.dto.orderBook.SubmitOrderBookResponseDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import utils.Constants;
import utils.UrlResources;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SubmitOrderBookRequest {

    private final SubmitOrderBookRequestDTO submitBook = new SubmitOrderBookRequestDTO();
    private final String token;

    @Getter
    private Response response;

    public SubmitOrderBookRequest() {
        this.token = TokenManager.accessToken();
    }

    public SubmitOrderBookResponseDTO addNewBook(int bookId, String customerName) {
        submitBook.setBookId(bookId);
        submitBook.setCustomerName(customerName);

        response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .baseUri(UrlResources.BASE_URI.getUrl())
                .basePath(UrlResources.ADD_BOOK_ORDER_ENDPOINT.getUrl())
                .body(submitBook).log().all()
                .post()
                .then().log().all()
                .body(matchesJsonSchemaInClasspath("schema_validation/NewBookAdded.json"))
                .extract().response();

        int statusCode = response.getStatusCode();

        return response.as(SubmitOrderBookResponseDTO.class);


    }

    public int getStatucCode() {
        return response.getStatusCode();
    }

}
