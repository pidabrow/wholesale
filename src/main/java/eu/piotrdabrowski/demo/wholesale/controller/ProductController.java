package eu.piotrdabrowski.demo.wholesale.controller;

import eu.piotrdabrowski.demo.wholesale.dto.ProductCreateDto;
import eu.piotrdabrowski.demo.wholesale.dto.ProductDto;
import eu.piotrdabrowski.demo.wholesale.service.ProductService;
import eu.piotrdabrowski.demo.wholesale.service.ValidationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ValidationService validationService;
    private final ProductService productService;

    @GetMapping("/products")
    @ApiOperation(value = "Find all Products.",
            response = ProductDto.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all Products retrieved successfully.")
    })
    public ResponseEntity<List<ProductDto>> handleGetProducts() {
        return new ResponseEntity<>(productService.findAll(), OK);
    }

    @GetMapping("/products/{id}")
    @ApiOperation(value = "Find Product by id.",
            response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product with given id retrieved successfully."),
            @ApiResponse(code = 404, message = "Product with given id doesn't exist.")
    })
    public ResponseEntity<ProductDto> handleGetProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), OK);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Product by id.",
            response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Product with given id deleted successfully with no content returned.")
    })
    public void handleDeleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/products")
    @ApiOperation(value = "Create Product.",
            response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "A new Product created successfully."),
            @ApiResponse(code = 400, message = "Cannot create new Product due to mandatory parameter missing or wrong format.")
    })
    public ResponseEntity<?> handleCreateProduct(@Valid @RequestBody ProductCreateDto productCreateDto,
                                                 BindingResult bindingResult) {
        ResponseEntity<?> validationError = validationService.validateBindingResult(bindingResult);
        if (validationError != null) {
            return validationError;
        }
        return new ResponseEntity<>(productService.createProduct(productCreateDto), CREATED);
    }

    @PutMapping("/products")
    @ApiOperation(value = "Update existing Product.",
            response = ProductDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Existing Product updated successfully."),
            @ApiResponse(code = 400, message = "Cannot update existing Product due to mandatory parameter missing or wrong format.")
    })
    public ResponseEntity<?> handleUpdateProduct(@Valid @RequestBody ProductDto productDto,
                                                 BindingResult bindingResult) {
        ResponseEntity<?> validationError = validationService.validateBindingResult(bindingResult);
        if (validationError != null) {
            return validationError;
        }

        return new ResponseEntity<>(productService.updateProduct(productDto), OK);
    }
}
