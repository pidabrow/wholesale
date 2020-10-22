package eu.piotrdabrowski.demo.wholesale.domain;

import static javax.persistence.GenerationType.AUTO;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = AUTO)
    protected Long id;

    @Setter
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="product", orphanRemoval = true)
    private Set<Availability> availability;

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
