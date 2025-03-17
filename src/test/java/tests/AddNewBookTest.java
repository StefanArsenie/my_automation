package tests;

import api_pom.dto.orderBook.SubmitOrderBookResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.SubmitOrderBookRequest;
import utils.Constants;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

 class AddNewBookTest {
    SubmitOrderBookRequest book;
    SubmitOrderBookResponseDTO response;

    @BeforeEach
    public void setUp() {
        book = new SubmitOrderBookRequest();
        response = book.addNewBook(Constants.BOOK_ID, Constants.CLIENT_NAME);
    }
    @Test
    void statusCodeIs200ForANewBookAdded() {
        assertThat(201, equalTo(book.getStatusCode()));
    }
    @Test
    void createdAttributeHasValueTrue() {
        assertThat(response.getCreated(), equalTo(true));
    }
    @Test
    void schemaValidationOfBookAdded() {
        book.getResponse().then().assertThat()
                .body(matchesJsonSchemaInClasspath("schema_validation/NewBookAddedResponse.json"));
    }
}
