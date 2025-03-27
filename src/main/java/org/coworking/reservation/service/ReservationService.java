package org.coworking.reservation.service;

import java.util.List;
import org.coworking.reservation.exception.ReservationNotFoundException;
import org.coworking.reservation.model.Reservation;
import org.coworking.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для управления бронированием рабочих мест.
 */
@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;

  /**
   * Конструктор сервиса бронирования.
   *
   * @param reservationRepository репозиторий бронирования
   */
  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  /**
   * Получает список всех бронирований.
   *
   * @return список объектов {@link Reservation}
   */
  public List<Reservation> getAllReservations() {
    return reservationRepository.getAllReservations();
  }

  /**
   * Получает бронирование по его идентификатору.
   *
   * @param id идентификатор бронирования
   * @return объект {@link Reservation}
   * @throws ReservationNotFoundException если бронирование не найдено
   */
  public Reservation getReservationById(int id) {
    Reservation reservation = reservationRepository.getReservationById(id);
    if (reservation == null) {
      throw new ReservationNotFoundException("Бронирование с ID " + id + " не найдено.");
    }
    return reservation;
  }

  /**
   * Получает отфильтрованный список бронирований по дате и рабочему пространству.
   *
   * @param date  дата бронирования
   * @param space рабочее пространство
   * @return список объектов {@link Reservation}, соответствующих критериям
   * @throws ReservationNotFoundException если бронирования не найдены
   */
  public List<Reservation> getFilteredReservations(String date, String space) {
    List<Reservation> filtered = reservationRepository.getFilteredReservations(date, space);
    if (filtered.isEmpty()) {
      throw new ReservationNotFoundException("Бронирования по заданным параметрам не найдены.");
    }
    return filtered;
  }
}
