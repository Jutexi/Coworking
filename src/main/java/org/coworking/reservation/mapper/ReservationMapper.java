package org.coworking.reservation.mapper;

import org.coworking.reservation.dto.ReservationDTO;
import org.coworking.reservation.model.CoworkingSpace;
import org.coworking.reservation.model.Reservation;
import org.coworking.reservation.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Маппер для преобразования между сущностью Reservation и DTO.
 */
@Component
public class ReservationMapper {

  /**
   * Преобразует сущность Reservation в DTO.
   *
   * @param reservation сущность Reservation
   * @return DTO ReservationDTO
   */
  public ReservationDTO toDto(Reservation reservation) {
    if (reservation == null) {
      return null;
    }

    ReservationDTO dto = new ReservationDTO();
    dto.setId(reservation.getId());
    dto.setDate(reservation.getDate());
    dto.setTimeSlot(reservation.getTimeSlot());

    // Преобразование списка пользователей в их идентификаторы
    dto.setUserIds(
        reservation.getUsers().stream()
            .map(User::getId)
            .collect(Collectors.toList())
    );

    // Преобразование коворкинга в его ID (если он существует)
    CoworkingSpace coworkingSpace = reservation.getCoworkingSpace();
    if (coworkingSpace != null) {
      dto.setCoworkingSpaceId(coworkingSpace.getId());
    }

    return dto;
  }

  /**
   * Преобразует DTO в сущность Reservation.
   *
   * @param dto DTO ReservationDTO
   * @return сущность Reservation
   */
  public Reservation toEntity(ReservationDTO dto) {
    if (dto == null) {
      return null;
    }

    Reservation reservation = new Reservation();
    reservation.setId(dto.getId());
    reservation.setDate(dto.getDate());
    reservation.setTimeSlot(dto.getTimeSlot());

    // Здесь можно добавить логику для загрузки пользователей и коворкинга по их ID
    // Например, через сервисы или репозитории

    return reservation;
  }

  /**
   * Преобразует список сущностей Reservation в список DTO.
   *
   * @param reservations список сущностей Reservation
   * @return список DTO ReservationDTO
   */
  public List<ReservationDTO> toDtoList(List<Reservation> reservations) {
    return reservations.stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  /**
   * Преобразует список DTO в список сущностей Reservation.
   *
   * @param dtos список DTO ReservationDTO
   * @return список сущностей Reservation
   */
  public List<Reservation> toEntityList(List<ReservationDTO> dtos) {
    return dtos.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }
}