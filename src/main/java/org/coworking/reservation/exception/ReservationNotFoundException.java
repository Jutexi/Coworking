package org.coworking.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое при отсутствии запрашиваемого бронирования.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationNotFoundException extends RuntimeException {

  /**
   * Создает исключение с указанным сообщением.
   *
   * @param message сообщение об ошибке
   */
  public ReservationNotFoundException(String message) {
    super(message);
  }
}
