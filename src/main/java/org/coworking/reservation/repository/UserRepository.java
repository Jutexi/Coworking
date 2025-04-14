package org.coworking.reservation.repository;

import org.coworking.reservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий пользователей.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

