package az.growlab.productapp.service.impl;

import az.growlab.productapp.domain.Product;
import az.growlab.productapp.dto.request.ProductRequest;
import az.growlab.productapp.dto.response.ProductResponse;
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
        ProductResponse productResponse = modelMapper.map(productRepository.findById(id), ProductResponse.class);
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return new ArrayList<>(productRepository.findAll()
                .stream().map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList()));
    }

    @Override
    public void insert(ProductRequest productRequest) {
        productRepository.save(modelMapper.map(productRequest, Product.class));
    }

    @Override
    public void update(Integer id, ProductRequest productRequest) {
        productRepository.update(id, productRequest);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
    }
}
