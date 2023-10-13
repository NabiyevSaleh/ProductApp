package az.growlab.productapp.repository.mapper;

import az.growlab.productapp.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .price(rs.getDouble("price"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();

    }
}
