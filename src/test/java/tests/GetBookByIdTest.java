package tests;

import api_pom.dto.getBookById.GetBookByIdResponseDTO;
import api_pom.dto.orderBook.SubmitOrderBookResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.GetBookByIdRequest;
import requests.SubmitOrderBookRequest;
import utils.Constants;

@Log4j2
class GetBookByIdTest {
    GetBookByIdRequest getBookByIdRequest;
    GetBookByIdResponseDTO responseDTO;
    SubmitOrderBookResponseDTO responseSubmit;
    SubmitOrderBookRequest submitOrder;
    String orderId;

    @BeforeEach
    void setUp() {
        submitOrder = new SubmitOrderBookRequest();
        responseSubmit = submitOrder.addNewBook(Constants.BOOK_ID, Constants.CLIENT_NAME);
        orderId = responseSubmit.getOrderId();
        log.info("Create orderId " + orderId);
        getBookByIdRequest = new GetBookByIdRequest();
        responseDTO = getBookByIdRequest.getBookById(orderId);
    }
    @Test
    void statusCodeIs200ForSpecificBookId() {
        Assertions.assertEquals(200, getBookByIdRequest.getStatusCode());
    }
    @Test
    void verifyThatSameOrderIdIsReceivedInResponse() {
        Assertions.assertEquals(orderId, responseSubmit.getOrderId());
    }
    @Test
    void verifyThatSameBookIdIsReceivedInResponse() {
        Assertions.assertEquals(6, responseDTO.getBookId());
        log.info("dasdfaasdasdasd " + responseDTO.getBookId());
    }
}
