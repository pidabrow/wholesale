package eu.piotrdabrowski.demo.wholesale.mapper;

import eu.piotrdabrowski.demo.wholesale.domain.Product;
import eu.piotrdabrowski.demo.wholesale.dto.ProductCreateDto;
import eu.piotrdabrowski.demo.wholesale.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }

    public Product toEntity(ProductCreateDto productCreateDto) {
        return Product.builder()
                .name(productCreateDto.getName())
                .build();
    }

    public Product merge(Product product, ProductDto productDto) {
        product.setName(productDto.getName());

        return product;
    }
}
