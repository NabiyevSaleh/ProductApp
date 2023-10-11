package az.growlab.productapp.repository;


import az.growlab.productapp.domain.Product;
import az.growlab.productapp.dto.request.ProductRequest;
import az.growlab.productapp.exception.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private static HashMap<Integer, Product> productLists = new HashMap<>();
    private Integer nextId = 1;

    public List<Product> findAll() {
        return new ArrayList<>(productLists.values());
    }

    public Product findById(Integer id) {
        if (!productLists.containsKey(id)) {
            throw new CustomNotFoundException("Product tapilmadi");
        }
        return productLists.get(id);
    }

    public void save(Product product) {
        Integer objectId = nextId++;
        LocalDate createdAt = LocalDate.now();
        product.setId(objectId);
        product.setCreatedAt(createdAt);
        productLists.put(product.getId(), product);
    }

    public void update(Integer id, ProductRequest productRequest) {
        if (!productLists.containsKey(id)) {
            throw new CustomNotFoundException("Product tapilmadi");
        }
        Product product = productLists.get(id);
        product.setId(id);
        product.setCreatedAt(LocalDate.now());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        productLists.put(id, product);
    }

    public void delete(Integer id) {
        if (!productLists.containsKey(id)) {
            throw new CustomNotFoundException("Product tapilmadi");
        }
        productLists.remove(id);
    }
}
