package org.coworking.reservation.dto;

import lombok.Data;

import java.util.List;

/**
 * DTO для бронирования.
 */
@Data
public class ReservationDTO {

  private Integer id;
  private String date;
  private String timeSlot;
  private Integer coworkingSpaceId;
  private List<Integer> userIds;

}