package az.growlab.productapp.dto.response2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private UUID uuid;
    private Integer code;
    private String message;
    private LocalDateTime timestamp;

}
