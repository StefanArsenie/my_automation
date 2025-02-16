package api_pom.dto.orderBook;

import lombok.Data;

@Data
public class SubmitOrderBookResponseDTO {

    private Boolean created;
    private String orderId;
}
