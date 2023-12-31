package az.growlab.productapp.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String name;
    private Double price;
    private LocalDate createdAt;
}
