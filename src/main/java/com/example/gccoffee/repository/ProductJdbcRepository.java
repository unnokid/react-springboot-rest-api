package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.example.gccoffee.JdbcUtils.toLocalDateTime;
import static com.example.gccoffee.JdbcUtils.toUUID;

@Repository
public class ProductJdbcRepository implements ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products", productRowMapper);
    }

    @Override
    public Product insert(Product product) {
        var update = jdbcTemplate.update("insert into products(product_id,product_name,category,price,description,create_at,update_at)" +
                "values(UNHEX(REPLACE(:productId, '-', '')), :productName, :category, :price, :description, :createAt,:updateAt)", toParamMap(product));
        if (update != 1) {
            throw new RuntimeException("Noting was inserted");
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        try{
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("select * from products where product_id = UUID_To_BIN(:productId)",
                            Collections.singletonMap("productId",productId.toString().getBytes()),
                            productRowMapper
                    ));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> findByName(String productName) {
        try{
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("select * from products where product_name = UUID_To_BIN(:productName)",
                            Collections.singletonMap("productName",productName),
                            productRowMapper
                    ));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return jdbcTemplate.query(
                "select * from products where category = :category",
                Collections.singletonMap("category",category.toString()),
                productRowMapper
        );
    }

    @Override
    public void deleteAll() {

    }

    private static final RowMapper<Product> productRowMapper = (resultSet, i) -> {
        var productId = toUUID(resultSet.getBytes("product_id"));
        var productName = resultSet.getString("product_name");
        var category = Category.valueOf(resultSet.getString("category"));
        var price = resultSet.getLong("price");
        var description = resultSet.getString("description");
        var createAt = toLocalDateTime(resultSet.getTimestamp("create_at"));
        var updateAt = toLocalDateTime(resultSet.getTimestamp("update_at"));
        return new Product(productId, productName, category, price, description, createAt, updateAt);
    };

    private Map<String, Object> toParamMap(Product product) {
        //자바 이중 중괄호 초기화
        //Map.of 사용시 Nullable 값을 처리해야함
        var paramMap = new HashMap<String, Object>();
        paramMap.put("productId", product.getProductId().toString().getBytes());
        paramMap.put("productName", product.getProductName());
        paramMap.put("category", product.getCategory().toString());
        paramMap.put("price", product.getPrice());
        paramMap.put("description", product.getDescription());
        paramMap.put("createAt", product.getCreateAt());
        paramMap.put("updateAt", product.getUpdateAt());
        return paramMap;
    }

}
