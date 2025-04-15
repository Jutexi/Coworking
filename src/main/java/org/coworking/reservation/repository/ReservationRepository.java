package org.coworking.reservation.repository;

import org.coworking.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
  @Query("SELECT r FROM Reservation r JOIN r.users u WHERE u.name = :userName")
  List<Reservation> findReservationsByUserName(@Param("userName") String userName);
}