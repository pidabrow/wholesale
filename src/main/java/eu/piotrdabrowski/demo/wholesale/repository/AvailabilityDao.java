package eu.piotrdabrowski.demo.wholesale.repository;

import eu.piotrdabrowski.demo.wholesale.domain.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityDao extends JpaRepository<Availability, Long> {

    @Query("SELECT a FROM Availability a "
            + "WHERE a.product.id = :productId ")
    List<Availability> findAllByProductId(@Param("productId") Long productId);
}
