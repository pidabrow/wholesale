package eu.piotrdabrowski.demo.wholesale.repository;

import eu.piotrdabrowski.demo.wholesale.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

}
