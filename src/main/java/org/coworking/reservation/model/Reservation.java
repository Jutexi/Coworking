package org.coworking.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 Класс, представляющий бронирование рабочего места.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
  private int id;
  private String coworkingSpace; // название коворкинга или помещения
  private String date;           // дата бронирования (формат: YYYY-MM-DD)
  private String timeSlot;       // временной интервал, например "09:00-12:00"
}
