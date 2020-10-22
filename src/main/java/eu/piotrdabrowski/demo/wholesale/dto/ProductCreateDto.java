package eu.piotrdabrowski.demo.wholesale.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDto {
    @NotNull
    private String name;
}
