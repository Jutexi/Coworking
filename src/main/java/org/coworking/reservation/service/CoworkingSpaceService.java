package org.coworking.reservation.service;

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

    public CoworkingSpaceService(CoworkingSpaceRepository coworkingSpaceRepository, CoworkingSpaceMapper coworkingSpaceMapper) {
        this.coworkingSpaceRepository = coworkingSpaceRepository;
        this.coworkingSpaceMapper = coworkingSpaceMapper;
    }

    /**
     * Создает новый коворкинг.
     *
     * @param coworkingSpaceDTO DTO коворкинга
     * @return созданный коворкинг в виде DTO
     */
    public CoworkingSpaceDto createCoworkingSpace(CoworkingSpaceDto coworkingSpaceDTO) {
        CoworkingSpace coworkingSpace = coworkingSpaceMapper.toEntity(coworkingSpaceDTO);
        CoworkingSpace savedCoworkingSpace = coworkingSpaceRepository.save(coworkingSpace);
        return coworkingSpaceMapper.toDto(savedCoworkingSpace);
    }

    /**
     * Получает коворкинг по ID.
     *
     * @param id идентификатор коворкинга
     * @return DTO коворкинга, если найден
     */
    public CoworkingSpaceDto getCoworkingSpaceById(Integer id) {
        Optional<CoworkingSpace> coworkingSpace = coworkingSpaceRepository.findById(id);
        return coworkingSpace.map(coworkingSpaceMapper::toDto).orElse(null);
    }

    /**
     * Обновляет существующий коворкинг.
     *
     * @param id идентификатор коворкинга
     * @param coworkingSpaceDTO обновленные данные коворкинга
     * @return обновленный коворкинг в виде DTO
     */
    public CoworkingSpaceDto updateCoworkingSpace(Integer id, CoworkingSpaceDto coworkingSpaceDTO) {
        Optional<CoworkingSpace> existingCoworkingSpace = coworkingSpaceRepository.findById(id);
        if (existingCoworkingSpace.isPresent()) {
            CoworkingSpace coworkingSpace = existingCoworkingSpace.get();
            coworkingSpace.setName(coworkingSpaceDTO.getName());
            // Логика обновления бронирований может быть добавлена здесь
            CoworkingSpace updatedCoworkingSpace = coworkingSpaceRepository.save(coworkingSpace);
            return coworkingSpaceMapper.toDto(updatedCoworkingSpace);
        }
        return null;
    }

    /**
     * Удаляет коворкинг по ID.
     *
     * @param id идентификатор коворкинга
     */
    public void deleteCoworkingSpace(Integer id) {
        coworkingSpaceRepository.deleteById(id);
    }

    /**
     * Получает список всех коворкингов.
     *
     * @return список DTO всех коворкингов
     */
    public List<CoworkingSpaceDto> getAllCoworkingSpaces() {
        List<CoworkingSpace> coworkingSpaces = coworkingSpaceRepository.findAll();
        return coworkingSpaceMapper.toDtoList(coworkingSpaces);
    }
}