package eu.piotrdabrowski.demo.wholesale.domain;

import static javax.persistence.GenerationType.AUTO;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Availability {

    @Id
    @GeneratedValue(strategy = AUTO)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Setter
    private BigDecimal price;

    @Setter
    private Long quantity;

    @Column(updatable = false)
    private ZonedDateTime dateCreated;

    private ZonedDateTime dateLastUpdated;

    @PrePersist
    public void onCreate() {
        this.dateCreated = ZonedDateTime.now();
        this.dateLastUpdated = ZonedDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.dateLastUpdated = ZonedDateTime.now();
    }
}
