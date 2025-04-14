package org.coworking.reservation.service;

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

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
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
        return reservationMapper.toDto(savedReservation);
    }

    /**
     * Получает бронирование по ID.
     *
     * @param id идентификатор бронирования
     * @return DTO бронирования, если найдено
     */
    public ReservationDTO getReservationById(Integer id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.map(reservationMapper::toDto).orElse(null);
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
            // Логика обновления пользователей и коворкинга может быть добавлена здесь
            Reservation updatedReservation = reservationRepository.save(reservation);
            return reservationMapper.toDto(updatedReservation);
        }
        return null;
    }

    /**
     * Удаляет бронирование по ID.
     *
     * @param id идентификатор бронирования
     */
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }

    /**
     * Получает список всех бронирований.
     *
     * @return список DTO всех бронирований
     */
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.toDtoList(reservations);
    }
}