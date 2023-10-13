package az.growlab.productapp.repository;


import az.growlab.productapp.domain.Product;
import az.growlab.productapp.repository.mapper.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final NamedParameterJdbcTemplate productJdbcTemplate;

    public List<Product> findAll() {
        var query = "SELECT * FROM PRODUCT";
        return productJdbcTemplate.query(query, new ProductRowMapper());
    }

    public Optional<Product> findById(Integer id) {
        var query = "SELECT * FROM PRODUCT WHERE id=:id";

        try {
            var product = productJdbcTemplate
                    .queryForObject(query,
                            new MapSqlParameterSource().addValue("id", id),
                            new ProductRowMapper());
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Product not found with id: {}", id);
            return Optional.empty();
        }
    }

    public void save(Product product) {
        var keyHolder = new GeneratedKeyHolder();
        var query = "INSERT INTO PRODUCT(name,price,created_at) VALUES (:name,:price,:createdAt)";
        productJdbcTemplate.update(query, new MapSqlParameterSource()
                        .addValue("name", product.getName())
                        .addValue("price", product.getPrice())
                        .addValue("createdAt", product.getCreatedAt())
                , keyHolder);
    }

    public void update(Integer id, Product product) {

        var query = "UPDATE PRODUCT SET name=:name, price=:price, created_at=:createdAt WHERE id=:id";
        productJdbcTemplate.update(query, new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("createdAt", product.getCreatedAt())
        );
    }

    public void delete(Integer id) {

        var query = "DELETE FROM PRODUCT WHERE id = :id";
        try {
            productJdbcTemplate.update(query, new MapSqlParameterSource()
                    .addValue("id", id));
        } catch (EmptyResultDataAccessException e) {
            log.error("Product not found with id: {}", id);
        }
    }
}
