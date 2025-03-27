package org.coworking.reservation.controller;

import java.util.List;
import org.coworking.reservation.model.Reservation;
import org.coworking.reservation.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для управления бронированиями.
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

  private final ReservationService reservationService;

  /**
   * Конструктор контроллера бронирований.
   *
   * @param reservationService сервис бронирований
   */
  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  /**
   * Получить список всех бронирований.
   *
   * @return список всех бронирований
   */
  @GetMapping("/all")
  public List<Reservation> getAllReservations() {
    return reservationService.getAllReservations();
  }

  /**
   * Получить бронирование по ID.
   *
   * @param id идентификатор бронирования
   * @return объект бронирования
   */
  @GetMapping("/{id}")
  public Reservation getReservationById(@PathVariable int id) {
    return reservationService.getReservationById(id);
  }

  /**
   * Фильтрация бронирований по дате и/или названию коворкинга.
   *
   * @param date  дата бронирования (необязательный параметр)
   * @param space название коворкинга (необязательный параметр)
   * @return список бронирований, соответствующих фильтрам
   */
  @GetMapping("/filter")
  public List<Reservation> getFilteredReservations(
      @RequestParam(required = false) String date,
      @RequestParam(required = false) String space) {
    return reservationService.getFilteredReservations(date, space);
  }
}
