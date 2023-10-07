package az.growlab.productapp.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Ad bosh ola bilmez")
    @Size(min = 2, max = 25, message = "Adin uzunlugu 2-25 araliginda olmalidir")
    private String name;

    @PositiveOrZero(message = "Qiymet menfi bilmez")
    private Double price;
}
