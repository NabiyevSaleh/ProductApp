package az.growlab.productapp.service.impl;

import az.growlab.productapp.domain.Product;
import az.growlab.productapp.dto.request2.ProductRequest;
import az.growlab.productapp.dto.response2.ProductResponse;
import az.growlab.productapp.exception.CustomNotFoundException;
import az.growlab.productapp.repository.ProductRepository;
import az.growlab.productapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductResponse getProduct(Integer id) {
        var product = productRepository.findById(id)
                .orElseThrow(()-> new CustomNotFoundException("Product not found with id: %s"));
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream().map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void insert(ProductRequest productRequest) {
        productRepository.save(modelMapper.map(productRequest, Product.class));
    }

    @Override
    public void update(Integer id, ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        productRepository.update(id, product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
    }
}
