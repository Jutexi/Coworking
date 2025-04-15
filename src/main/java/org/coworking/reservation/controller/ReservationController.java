package org.coworking.reservation.controller;

import org.coworking.reservation.dto.ReservationDTO;
import org.coworking.reservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

  private final ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  /**
   * Создает новое бронирование.
   *
   * @param reservationDTO DTO бронирования
   * @return созданное бронирование
   */
  @PostMapping
  public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
    ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
    return ResponseEntity.ok(createdReservation);
  }

  /**
   * Получает бронирование по ID.
   *
   * @param id идентификатор бронирования
   * @return найденное бронирование
   */
  @GetMapping("/{id}")
  public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Integer id) {
    ReservationDTO reservation = reservationService.getReservationById(id);
    if (reservation != null) {
      return ResponseEntity.ok(reservation);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * Обновляет существующее бронирование.
   *
   * @param id идентификатор бронирования
   * @param reservationDTO обновленные данные бронирования
   * @return обновленное бронирование
   */
  @PutMapping("/{id}")
  public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Integer id, @RequestBody ReservationDTO reservationDTO) {
    ReservationDTO updatedReservation = reservationService.updateReservation(id, reservationDTO);
    if (updatedReservation != null) {
      return ResponseEntity.ok(updatedReservation);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * Удаляет бронирование по ID.
   *
   * @param id идентификатор бронирования
   * @return статус операции
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
    reservationService.deleteReservation(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Получает список всех бронирований.
   *
   * @return список бронирований
   */
  @GetMapping
  public ResponseEntity<List<ReservationDTO>> getAllReservations() {
    List<ReservationDTO> reservations = reservationService.getAllReservations();
    return ResponseEntity.ok(reservations);
  }

  /**
   * Получает список бронирований по имени пользователя.
   *
   * @param userName имя пользователя
   * @return список бронирований
   */
  @GetMapping("/filter")
  public ResponseEntity<List<ReservationDTO>> getFilteredReservations(@RequestParam String userName) {
    List<ReservationDTO> reservations = reservationService.getReservationsByUserName(userName);
    return ResponseEntity.ok(reservations);
  }
  //@GetMapping("/user/{userName}")
  //public ResponseEntity<List<ReservationDTO>> getReservationsByUserName(@PathVariable String userName) {
  //  List<ReservationDTO> reservations = reservationService.getReservationsByUserName(userName);
  //  return ResponseEntity.ok(reservations);
  //}
}