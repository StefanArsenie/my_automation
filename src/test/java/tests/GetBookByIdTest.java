package tests;

import api_pom.dto.getBookById.GetBookByIdResponseDTO;
import api_pom.dto.orderBook.SubmitOrderBookResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.GetBookByIdRequest;
import requests.SubmitOrderBookRequest;
import utils.Constants;

class GetBookByIdTest {
    GetBookByIdRequest getBookByIdRequest;
    GetBookByIdResponseDTO responseDTO;
    SubmitOrderBookResponseDTO responseSubmit;
    SubmitOrderBookRequest submitOrder;



    @BeforeEach
    void setUp() {
        submitOrder = new SubmitOrderBookRequest();
        responseSubmit = submitOrder.addNewBook(Constants.BOOK_ID, Constants.CLIENT_NAME);
        String orderId = responseSubmit.getOrderId();
        System.out.println("Create orderId: " + orderId);
        getBookByIdRequest = new GetBookByIdRequest();
        responseDTO = getBookByIdRequest.getBookById(orderId);
    }

    @Test
    void statusCodeIs200ForSpecificBookId() {
        Assertions.assertEquals(200, getBookByIdRequest.getStatusCode());
    }
}
