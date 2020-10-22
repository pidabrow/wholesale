package eu.piotrdabrowski.demo.wholesale.mapper;

import eu.piotrdabrowski.demo.wholesale.domain.Availability;
import eu.piotrdabrowski.demo.wholesale.dto.AvailabilityCreateDto;
import eu.piotrdabrowski.demo.wholesale.dto.AvailabilityDto;
import eu.piotrdabrowski.demo.wholesale.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvailabilityMapper {

    private final ProductDao productDao;

    public AvailabilityDto toDto(Availability availability) {
        return AvailabilityDto.builder()
                .id(availability.getId())
                .price(availability.getPrice())
                .productId(availability.getProduct().getId())
                .quantity(availability.getQuantity())
                .build();
    }

    public Availability toEntity(AvailabilityCreateDto availabilityCreateDto) {
        return Availability.builder()
                .price(availabilityCreateDto.getPrice())
                .quantity(availabilityCreateDto.getQuantity())
                .product(productDao.findById(availabilityCreateDto.getProductId()).get())
                .build();
    }

    public Availability merge(Availability availability, AvailabilityDto availabilityDto) {
        availability.setPrice(availabilityDto.getPrice());
        availability.setQuantity(availabilityDto.getQuantity());

        return availability;
    }
}
