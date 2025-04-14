package org.coworking.reservation.dto;

import lombok.Data;
import java.util.List;

/**
 * DTO для пользователя.
 */
@Data
public class UserDTO {

  private Integer id;
  private String name;
  private String email;
  private List<Integer> reservationIds;  // Список бронирований, связанных с пользователем

}