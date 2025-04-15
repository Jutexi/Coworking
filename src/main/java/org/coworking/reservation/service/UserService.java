package org.coworking.reservation.service;

import org.coworking.reservation.cache.UserCache;
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
  private final UserCache userCache;

  public UserService(UserRepository userRepository,
                     UserMapper userMapper,
                     UserCache userCache) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.userCache = userCache;
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
    userCache.put(savedUser.getId().longValue(), savedUser);
    return userMapper.toDto(savedUser);
  }

  /**
   * Получает пользователя по ID, сначала из кэша.
   *
   * @param id идентификатор пользователя
   * @return DTO пользователя, если найден
   */
  public UserDTO getUserById(Integer id) {
    Long key = id.longValue();
    User cached = userCache.get(key);
    if (cached != null) {
      return userMapper.toDto(cached);
    }

    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      userCache.put(key, user.get());
      return userMapper.toDto(user.get());
    }
    return null;
  }

  /**
   * Обновляет существующего пользователя и обновляет кэш.
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
      // Обновление бронирований при необходимости

      User updatedUser = userRepository.save(user);
      userCache.put(updatedUser.getId().longValue(), updatedUser);
      return userMapper.toDto(updatedUser);
    }
    return null;
  }

  /**
   * Удаляет пользователя по ID и удаляет его из кэша.
   *
   * @param id идентификатор пользователя
   */
  public void deleteUser(Integer id) {
    userRepository.deleteById(id);
    userCache.remove(id.longValue());
  }

  /**
   * Получает список всех пользователей (без кэша).
   *
   * @return список DTO всех пользователей
   */
  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return userMapper.toDtoList(users);
  }
}
