package org.coworking.reservation.cache;

import org.coworking.reservation.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationCache extends LfuCache<Reservation> {
  public ReservationCache() {
    super(100); // можно поменять размер кэша
  }
}
