package org.coworking.reservation.repository;

import org.coworking.reservation.model.CoworkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoworkingSpaceRepository extends JpaRepository<CoworkingSpace, Integer> {
}

