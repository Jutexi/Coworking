package org.coworking.reservation.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.coworking.reservation.dto.CoworkingSpaceDto;
import org.coworking.reservation.model.CoworkingSpace;
import org.coworking.reservation.model.Reservation;
import org.springframework.stereotype.Component;

/**
 * Маппер для преобразования между сущностью CoworkingSpace и DTO.
 */
@Component
public class CoworkingSpaceMapper {

  /**
   * Преобразует сущность CoworkingSpace в DTO.
   *
   * @param coworkingSpace сущность CoworkingSpace
   * @return DTO CoworkingSpaceDTO
   */
  public CoworkingSpaceDto toDto(CoworkingSpace coworkingSpace) {
    if (coworkingSpace == null) {
      return null;
    }

    CoworkingSpaceDto dto = new CoworkingSpaceDto();
    dto.setId(coworkingSpace.getId());
    dto.setName(coworkingSpace.getName());
    dto.setReservationIds(
        coworkingSpace.getReservations().stream()
            .map(Reservation::getId)
            .collect(Collectors.toList())
    );

    return dto;
  }

  /**
   * Преобразует DTO в сущность CoworkingSpace.
   *
   * @param dto DTO CoworkingSpaceDTO
   * @return сущность CoworkingSpace
   */
  public CoworkingSpace toEntity(CoworkingSpaceDto dto) {
    if (dto == null) {
      return null;
    }

    CoworkingSpace coworkingSpace = new CoworkingSpace();
    coworkingSpace.setId(dto.getId());
    coworkingSpace.setName(dto.getName());

    // Логика для загрузки бронирований по их ID может быть добавлена здесь, если необходимо

    return coworkingSpace;
  }

  /**
   * Преобразует список сущностей CoworkingSpace в список DTO.
   *
   * @param coworkingSpaces список сущностей CoworkingSpace
   * @return список DTO CoworkingSpaceDTO
   */
  public List<CoworkingSpaceDto> toDtoList(List<CoworkingSpace> coworkingSpaces) {
    if (coworkingSpaces == null) {
      return null;
    }

    return coworkingSpaces.stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  /**
   * Преобразует список DTO в список сущностей CoworkingSpace.
   *
   * @param dtos список DTO CoworkingSpaceDTO
   * @return список сущностей CoworkingSpace
   */
  public List<CoworkingSpace> toEntityList(List<CoworkingSpaceDto> dtos) {
    if (dtos == null) {
      return null;
    }

    return dtos.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }
}