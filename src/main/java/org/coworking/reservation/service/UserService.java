package org.coworking.reservation.service;

import org.coworking.reservation.dto.UserDTO;
import org.coworking.reservation.mapper.UserMapper;
import org.coworking.reservation.model.User;
import org.coworking.reservation.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  /**
   * Создает нового пользователя.
   *
   * @param userDTO DTO пользователя
   * @return созданный пользователь в виде DTO
   */
  public UserDTO createUser(UserDTO userDTO) {
    User user = userMapper.toEntity(userDTO);
    User savedUser = userRepository.save(user);
    return userMapper.toDto(savedUser);
  }

  /**
   * Получает пользователя по ID.
   *
   * @param id идентификатор пользователя
   * @return DTO пользователя, если найден
   */
  public UserDTO getUserById(Integer id) {
    Optional<User> user = userRepository.findById(id);
    return user.map(userMapper::toDto).orElse(null);
  }

  /**
   * Обновляет существующего пользователя.
   *
   * @param id идентификатор пользователя
   * @param userDTO обновленные данные пользователя
   * @return обновленный пользователь в виде DTO
   */
  public UserDTO updateUser(Integer id, UserDTO userDTO) {
    Optional<User> existingUser = userRepository.findById(id);
    if (existingUser.isPresent()) {
      User user = existingUser.get();
      user.setName(userDTO.getName());
      user.setEmail(userDTO.getEmail());
      // Логика обновления бронирований может быть добавлена здесь
      User updatedUser = userRepository.save(user);
      return userMapper.toDto(updatedUser);
    }
    return null;
  }

  /**
   * Удаляет пользователя по ID.
   *
   * @param id идентификатор пользователя
   */
  public void deleteUser(Integer id) {
    userRepository.deleteById(id);
  }

  /**
   * Получает список всех пользователей.
   *
   * @return список DTO всех пользователей
   */
  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return userMapper.toDtoList(users);
  }
}