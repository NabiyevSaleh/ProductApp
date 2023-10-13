package az.growlab.productapp.service;

import az.growlab.productapp.dto.request2.ProductRequest;
import az.growlab.productapp.dto.response2.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse getProduct(Integer id);
    List<ProductResponse> getAllProducts();
    void insert(ProductRequest productRequest);
    void update(Integer id, ProductRequest productRequest);
    void delete(Integer id);
}
