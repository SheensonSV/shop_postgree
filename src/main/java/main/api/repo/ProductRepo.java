package main.api.repo;

import main.api.entitys.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
    @Query(value = "SELECT u FROM products u WHERE u.name = ?1", nativeQuery = true)
    List<Product> findProductByName(String name);
}
