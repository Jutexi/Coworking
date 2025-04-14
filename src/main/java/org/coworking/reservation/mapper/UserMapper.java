package org.coworking.reservation.mapper;

import org.coworking.reservation.dto.UserDTO;
import org.coworking.reservation.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Маппер для преобразования между сущностью User и DTO.
 */
@Component
public class UserMapper {

    /**
     * Преобразует сущность User в DTO.
     *
     * @param user сущность User
     * @return DTO UserDTO
     */
    public UserDTO toDto(User user) {
        return Optional.ofNullable(user)
                .map(u -> {
                    UserDTO dto = new UserDTO();
                    dto.setId(u.getId());
                    dto.setName(u.getName());
                    dto.setEmail(u.getEmail());
                    dto.setReservationIds(
                            u.getReservations().stream()
                                    .map(reservation -> reservation.getId())
                                    .collect(Collectors.toList())
                    );
                    return dto;
                })
                .orElse(null);
    }

    /**
     * Преобразует DTO в сущность User.
     *
     * @param dto DTO UserDTO
     * @return сущность User
     */
    public User toEntity(UserDTO dto) {
        return Optional.ofNullable(dto)
                .map(d -> {
                    User user = new User();
                    user.setId(d.getId());
                    user.setName(d.getName());
                    user.setEmail(d.getEmail());
                    // Логика для загрузки бронирований может быть добавлена здесь
                    return user;
                })
                .orElse(null);
    }
    public List<UserDTO> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    public List<User> toEntityList(List<UserDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}