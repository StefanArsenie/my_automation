package tests;

import api_pom.dto.orderBook.SubmitOrderBookResponseDTO;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import requests.SubmitOrderBookRequest;
import utils.Constants;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

public class AddNewBook {
    int bookId = Constants.BOOK_ID;
    String customerName = Constants.CLIENT_NAME;

    SubmitOrderBookRequest book = new SubmitOrderBookRequest();
    SubmitOrderBookResponseDTO response = book.addNewBook(bookId, customerName);

    @Test
    public void statusCodeIs200() {

        assertThat(201, equalTo(book.getStatucCode()));

    }

    @Test
    public void createdKeyHasValueTrue() {
        assertThat(response.getCreated(), equalTo(true));
    }

    @Test
    public void schemaValidationOfBookAdded() {
        book.getResponse().then().assertThat()
                .body(matchesJsonSchemaInClasspath("schema_validation/NewBookAdded.json"));
    }
}
