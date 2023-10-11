package az.growlab.productapp.service;

import az.growlab.productapp.dto.request.ProductRequest;
import az.growlab.productapp.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse getProduct(Integer id);
    List<ProductResponse> getAllProducts();
    void insert(ProductRequest productRequest);
    void update(Integer id, ProductRequest productRequest);
    void delete(Integer id);
}
