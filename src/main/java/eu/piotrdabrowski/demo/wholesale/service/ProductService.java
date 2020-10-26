package eu.piotrdabrowski.demo.wholesale.service;

import eu.piotrdabrowski.demo.wholesale.domain.Product;
import eu.piotrdabrowski.demo.wholesale.dto.ProductCreateDto;
import eu.piotrdabrowski.demo.wholesale.dto.ProductDto;
import eu.piotrdabrowski.demo.wholesale.exception.ResourceNotFoundException;
import eu.piotrdabrowski.demo.wholesale.mapper.ProductMapper;
import eu.piotrdabrowski.demo.wholesale.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productDao.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        return productDao.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(Product.class, id));
    }

    @Transactional
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    @Transactional
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        final Product created = productDao.save(productMapper.toEntity(productCreateDto));

        return productMapper.toDto(created);
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        final Optional<Product> productById = productDao.findById(productDto.getId());

        return productById.map(p -> {
            final Product merged = productMapper.merge(p, productDto);
            return productMapper.toDto(merged);
        }).orElseThrow(() -> new ResourceNotFoundException(Product.class, productDto.getId()));
    }
}
