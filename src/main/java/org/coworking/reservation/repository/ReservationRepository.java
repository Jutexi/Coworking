package org.coworking.reservation.repository;

import java.util.List;
import org.coworking.reservation.model.Reservation;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для хранения и получения данных о бронированиях.
 */
@Repository
public class ReservationRepository {

  private final List<Reservation> reservations = List.of(
    new Reservation(0, "Main Hall", "2025-03-18", "09:00-12:00"),
    new Reservation(1, "Creative Space", "2025-03-18", "13:00-16:00"),
    new Reservation(2, "Conference Room", "2025-03-19", "10:00-12:00")
  );

  /**
   * Получает список всех бронирований.
   *
   * @return список объектов {@link Reservation}
   */
  public List<Reservation> getAllReservations() {
    return reservations;
  }

  /**
   * Получает бронирование по его идентификатору.
   *
   * @param id идентификатор бронирования
   * @return объект {@link Reservation} или {@code null}, если бронирование не найдено
   */
  public Reservation getReservationById(int id) {
    if (id >= 0 && id < reservations.size()) {
      return reservations.get(id);
    }
    return null;
  }

  /**
   * Фильтрует бронирования по дате и/или названию рабочего пространства.
   *
   * @param date  дата бронирования (может быть {@code null})
   * @param space название рабочего пространства (может быть {@code null})
   * @return список объектов {@link Reservation}, соответствующих критериям
   */
  public List<Reservation> getFilteredReservations(String date, String space) {
    return reservations.stream()
      .filter(reservation -> (date == null || reservation.getDate().equals(date))
        && (space == null || reservation.getCoworkingSpace().equalsIgnoreCase(space)))
      .toList();
  }
}
