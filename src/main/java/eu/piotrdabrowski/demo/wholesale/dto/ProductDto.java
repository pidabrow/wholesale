package eu.piotrdabrowski.demo.wholesale.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ProductDto {
    private Long id;

    @NotBlank
    private String name;
}
