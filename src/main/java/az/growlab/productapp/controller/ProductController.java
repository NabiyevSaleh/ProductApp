package az.growlab.productapp.controller;

import az.growlab.productapp.dto.request2.ProductRequest;
import az.growlab.productapp.dto.response2.ProductResponse;
import az.growlab.productapp.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid ProductRequest productRequest) {
        productService.insert(productRequest);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Integer id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody @Valid ProductRequest productRequest) {
        productService.update(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }
}
