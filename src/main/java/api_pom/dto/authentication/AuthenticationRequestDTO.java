package api_pom.dto.authentication;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {

    private String clientName;
    private String clientEmail;

}
