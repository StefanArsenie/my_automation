package utils;

import lombok.Getter;

public enum UrlResources {

    BASE_URI("https://simple-books-api.glitch.me"),
    AUTH_ENDPOINT("/api-clients"),
    ADD_BOOK_ORDER_ENDPOINT("/orders"),
    GET_BOOK_ORDER_ENDPOINT("/orders/:orderId"),
    UPDATE_BOOK_ORDER_ENDPOINT("/orders/:orderId"),
    DELETE_BOOK_ORDER_ENDPOINT("/orders/:orderId"),
    GET_ALL_BOOKS_ORDER_ENDPOINT("/orders");

    @Getter
    private final String url;

    UrlResources (String endpoint) {
        this.url = endpoint;
    }


}


