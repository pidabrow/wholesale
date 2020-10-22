package eu.piotrdabrowski.demo.wholesale.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class AvailabilityDto {

    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Long quantity;
}
