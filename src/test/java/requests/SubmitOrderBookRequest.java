package requests;

import api_pom.dto.orderBook.SubmitOrderBookRequestDTO;
import api_pom.dto.orderBook.SubmitOrderBookResponseDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import utils.UrlResources;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
@Log4j2
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
                .when()
                .post()
                .then().log().all()
                .body(matchesJsonSchemaInClasspath("schema_validation/NewBookAddedResponse.json"))
                .extract().response();

        return response.as(SubmitOrderBookResponseDTO.class);

    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

}
