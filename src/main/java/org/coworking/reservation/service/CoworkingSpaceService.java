package org.coworking.reservation.service;

import org.coworking.reservation.cache.CoworkingSpaceCache;
import org.coworking.reservation.dto.CoworkingSpaceDto;
import org.coworking.reservation.mapper.CoworkingSpaceMapper;
import org.coworking.reservation.model.CoworkingSpace;
import org.coworking.reservation.repository.CoworkingSpaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoworkingSpaceService {

    private final CoworkingSpaceRepository coworkingSpaceRepository;
    private final CoworkingSpaceMapper coworkingSpaceMapper;
    private final CoworkingSpaceCache coworkingSpaceCache;

    public CoworkingSpaceService(CoworkingSpaceRepository coworkingSpaceRepository,
                                 CoworkingSpaceMapper coworkingSpaceMapper,
                                 CoworkingSpaceCache coworkingSpaceCache) {
        this.coworkingSpaceRepository = coworkingSpaceRepository;
        this.coworkingSpaceMapper = coworkingSpaceMapper;
        this.coworkingSpaceCache = coworkingSpaceCache;
    }

    /**
     * Создает новый коворкинг и сохраняет его в кэше.
     *
     * @param coworkingSpaceDTO DTO коворкинга
     * @return созданный коворкинг в виде DTO
     */
    public CoworkingSpaceDto createCoworkingSpace(CoworkingSpaceDto coworkingSpaceDTO) {
        CoworkingSpace coworkingSpace = coworkingSpaceMapper.toEntity(coworkingSpaceDTO);
        CoworkingSpace saved = coworkingSpaceRepository.save(coworkingSpace);
        coworkingSpaceCache.put(saved.getId().longValue(), saved);
        return coworkingSpaceMapper.toDto(saved);
    }

    /**
     * Получает коворкинг по ID, сначала из кэша.
     *
     * @param id идентификатор коворкинга
     * @return DTO коворкинга, если найден
     */
    public CoworkingSpaceDto getCoworkingSpaceById(Integer id) {
        Long key = id.longValue();
        CoworkingSpace cached = coworkingSpaceCache.get(key);
        if (cached != null) {
            return coworkingSpaceMapper.toDto(cached);
        }

        Optional<CoworkingSpace> coworking = coworkingSpaceRepository.findById(id);
        if (coworking.isPresent()) {
            coworkingSpaceCache.put(key, coworking.get());
            return coworkingSpaceMapper.toDto(coworking.get());
        }
        return null;
    }

    /**
     * Обновляет коворкинг в БД и кэше.
     *
     * @param id идентификатор коворкинга
     * @param coworkingSpaceDTO обновленные данные
     * @return обновленный коворкинг в виде DTO
     */
    public CoworkingSpaceDto updateCoworkingSpace(Integer id, CoworkingSpaceDto coworkingSpaceDTO) {
        Optional<CoworkingSpace> existing = coworkingSpaceRepository.findById(id);
        if (existing.isPresent()) {
            CoworkingSpace coworking = existing.get();
            coworking.setName(coworkingSpaceDTO.getName());
            CoworkingSpace updated = coworkingSpaceRepository.save(coworking);
            coworkingSpaceCache.put(updated.getId().longValue(), updated);
            return coworkingSpaceMapper.toDto(updated);
        }
        return null;
    }

    /**
     * Удаляет коворкинг из БД и кэша.
     *
     * @param id идентификатор коворкинга
     */
    public void deleteCoworkingSpace(Integer id) {
        coworkingSpaceRepository.deleteById(id);
        coworkingSpaceCache.remove(id.longValue());
    }

    /**
     * Получает список всех коворкингов (без кэша).
     *
     * @return список DTO всех коворкингов
     */
    public List<CoworkingSpaceDto> getAllCoworkingSpaces() {
        List<CoworkingSpace> spaces = coworkingSpaceRepository.findAll();
        return coworkingSpaceMapper.toDtoList(spaces);
    }
}
