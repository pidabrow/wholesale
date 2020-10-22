package eu.piotrdabrowski.demo.wholesale.controller;

import eu.piotrdabrowski.demo.wholesale.dto.AvailabilityCreateDto;
import eu.piotrdabrowski.demo.wholesale.dto.AvailabilityDto;
import eu.piotrdabrowski.demo.wholesale.dto.ProductDto;
import eu.piotrdabrowski.demo.wholesale.service.AvailabilityService;
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
public class AvailabilityController {

    private final ValidationService validationService;
    private final AvailabilityService availabilityService;

    @GetMapping("/availabilities")
    @ApiOperation(value = "Find all Availabilities for given Product (by productId).",
            response = AvailabilityDto.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all Availabilities for given Product (by productId) retrieved successfully.")
    })
    public ResponseEntity<List<AvailabilityDto>> handleGetAvailabilities(@RequestParam Long productId) {
        return new ResponseEntity<>(availabilityService.findAllByProductId(productId), HttpStatus.OK);
    }

    @PostMapping("/availabilities")
    @ApiOperation(value = "Create Availability for given Product.",
            response = AvailabilityDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "A new Availability for given Product created successfully."),
            @ApiResponse(code = 400, message = "Cannot create new Availability due to mandatory parameter missing or wrong format.")
    })
    public ResponseEntity<?> handleCreateAvailability(@Valid @RequestBody AvailabilityCreateDto availabilityCreateDto,
                                                      BindingResult bindingResult) {
        ResponseEntity<?> validationError = validationService.validateBindingResult(bindingResult);
        if (validationError != null) {
            return validationError;
        }

        return new ResponseEntity<>(availabilityService.createAvailability(availabilityCreateDto), CREATED);
    }

    @PutMapping("/availabilities")
    @ApiOperation(value = "Update existing Availability for given Product.",
            response = AvailabilityDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Existing Availability updated successfully."),
            @ApiResponse(code = 400, message = "Cannot update existing Availability due to mandatory parameter missing or wrong format.")
    })
    public ResponseEntity<?> handleUpdateAvailability(@Valid @RequestBody AvailabilityDto availabilityDto,
                                                      BindingResult bindingResult) {
        ResponseEntity<?> validationError = validationService.validateBindingResult(bindingResult);
        if (validationError != null) {
            return validationError;
        }

        return new ResponseEntity<>(availabilityService.updateAvailability(availabilityDto), OK);

    }
}
