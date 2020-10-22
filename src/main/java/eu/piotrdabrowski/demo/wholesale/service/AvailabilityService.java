package eu.piotrdabrowski.demo.wholesale.service;

import eu.piotrdabrowski.demo.wholesale.domain.Availability;
import eu.piotrdabrowski.demo.wholesale.dto.AvailabilityCreateDto;
import eu.piotrdabrowski.demo.wholesale.dto.AvailabilityDto;
import eu.piotrdabrowski.demo.wholesale.exception.ResourceNotFoundException;
import eu.piotrdabrowski.demo.wholesale.mapper.AvailabilityMapper;
import eu.piotrdabrowski.demo.wholesale.repository.AvailabilityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AvailabilityDao availabilityDao;
    private final AvailabilityMapper availabilityMapper;

    @Transactional
    public AvailabilityDto createAvailability(AvailabilityCreateDto availabilityCreateDto) {
        final Availability created = availabilityMapper.toEntity(availabilityCreateDto);
        final Availability saved = availabilityDao.save(created);

        return availabilityMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<AvailabilityDto> findAllByProductId(Long productId) {
        return availabilityDao.findAllByProductId(productId)
                .stream()
                .map(availabilityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AvailabilityDto updateAvailability(AvailabilityDto availabilityDto) {
        final Optional<Availability> availabilityById = availabilityDao.findById(availabilityDto.getId());

        return availabilityById.map(a -> {
            final Availability merged = availabilityMapper.merge(a, availabilityDto);
            return availabilityMapper.toDto(merged);
        }).orElseThrow(() -> new ResourceNotFoundException(Availability.class, availabilityDto.getId()));
    }
}
