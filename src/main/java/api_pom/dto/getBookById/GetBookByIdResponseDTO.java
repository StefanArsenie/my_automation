package api_pom.dto.getBookById;

import lombok.Data;

@Data
public class GetBookByIdResponseDTO {

    private String id;
    private int bookId;
    private String customerName;
    private String createdBy;
    private int quantity;
    private Long timestamp;
}
