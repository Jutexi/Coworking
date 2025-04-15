package org.coworking.reservation.service;

import org.coworking.reservation.cache.ReservationCache;
import org.coworking.reservation.dto.ReservationDTO;
import org.coworking.reservation.mapper.ReservationMapper;
import org.coworking.reservation.model.Reservation;
import org.coworking.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationCache reservationCache;

    public ReservationService(ReservationRepository reservationRepository,
                              ReservationMapper reservationMapper,
                              ReservationCache reservationCache) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.reservationCache = reservationCache;
    }

    /**
     * Создает новое бронирование.
     *
     * @param reservationDTO DTO бронирования
     * @return созданное бронирование в виде DTO
     */
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        Reservation savedReservation = reservationRepository.save(reservation);
        reservationCache.put(savedReservation.getId().longValue(), savedReservation);
        return reservationMapper.toDto(savedReservation);
    }

    /**
     * Получает бронирование по ID, сначала из кэша.
     *
     * @param id идентификатор бронирования
     * @return DTO бронирования, если найдено
     */
    public ReservationDTO getReservationById(Integer id) {
        Long key = id.longValue();
        Reservation cached = reservationCache.get(key);
        if (cached != null) {
            return reservationMapper.toDto(cached);
        }

        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            reservationCache.put(key, reservation.get());
            return reservationMapper.toDto(reservation.get());
        }
        return null;
    }

    /**
     * Обновляет существующее бронирование.
     *
     * @param id идентификатор бронирования
     * @param reservationDTO обновленные данные бронирования
     * @return обновленное бронирование в виде DTO
     */
    public ReservationDTO updateReservation(Integer id, ReservationDTO reservationDTO) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            Reservation reservation = existingReservation.get();
            reservation.setDate(reservationDTO.getDate());
            reservation.setTimeSlot(reservationDTO.getTimeSlot());
            // Обновление связей можно добавить здесь

            Reservation updatedReservation = reservationRepository.save(reservation);
            reservationCache.put(updatedReservation.getId().longValue(), updatedReservation);
            return reservationMapper.toDto(updatedReservation);
        }
        return null;
    }

    /**
     * Удаляет бронирование по ID и из кэша.
     *
     * @param id идентификатор бронирования
     */
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
        reservationCache.remove(id.longValue());
    }

    /**
     * Получает список всех бронирований (без кэша).
     *
     * @return список DTO всех бронирований
     */
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.toDtoList(reservations);
    }

    /**
     * Получает список бронирований по имени пользователя.
     *
     * @param userName имя пользователя
     * @return список DTO бронирований
     */
    public List<ReservationDTO> getReservationsByUserName(String userName) {
        List<Reservation> reservations = reservationRepository.findReservationsByUserName(userName);
        return reservationMapper.toDtoList(reservations);
    }
}
