package az.growlab.productapp.controller;

import az.growlab.productapp.domain.Product;
import az.growlab.productapp.dto.Request.ProductRequest;
import az.growlab.productapp.dto.Response.ProductResponse;
import az.growlab.productapp.exception.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ModelMapper modelMapper;
    private static HashMap<Integer, Product> productLists = new HashMap<>();
    private Integer nextId = 1;


    @PostMapping("/insertProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid ProductRequest productRequest){
        Product product = modelMapper.map(productRequest, Product.class);

        Integer objectId = nextId++;
        LocalDate createdAt = LocalDate.now();
        product.setId(objectId);
        product.setCreatedAt(createdAt);
        productLists.put(product.getId(), product);
    }

    @GetMapping("/getById/{id}")
    public ProductResponse getProductById(@PathVariable Integer id) {

        if (productLists.containsKey(id)) {
            ProductResponse productResponse = modelMapper.map(productLists.get(id), ProductResponse.class);
            return productResponse;
        } else throw new CustomNotFoundException("Product tapilmadi");
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return new ArrayList<>(productLists.values().stream().map(product -> modelMapper.map(product, ProductResponse.class)).collect(Collectors.toList()));
    }


    @PutMapping("/updateProduct/{id}")
    public ProductResponse update(@PathVariable Integer id, @RequestBody @Valid ProductRequest productRequest) {

        if (productLists.containsKey(id)) {
            Product product = modelMapper.map(productRequest, Product.class);
            product.setId(id);
            product.setCreatedAt(LocalDate.now());
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());

            productLists.put(id, product);

            return modelMapper.map(product, ProductResponse.class);
        } else throw new CustomNotFoundException("Product tapilmadi");
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void delete(@PathVariable Integer id) {
        if (productLists.containsKey(id)) {
            productLists.remove(id);
        } else throw new CustomNotFoundException("Product tapilmadi");
    }
}
