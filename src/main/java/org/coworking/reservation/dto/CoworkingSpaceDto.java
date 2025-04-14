package org.coworking.reservation.dto;

import java.util.List;
import lombok.Data;

/**
 * DTO для коворкинг-пространства.
 */
@Data
public class CoworkingSpaceDto {

  private Integer id;
  private String name;
  private List<Integer> reservationIds;  // Список бронирований, связанных с этим коворкингом

}