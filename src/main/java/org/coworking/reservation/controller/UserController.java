package org.coworking.reservation.controller;

import org.coworking.reservation.dto.UserDTO;
import org.coworking.reservation.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Создает нового пользователя.
   *
   * @param userDTO DTO пользователя
   * @return созданный пользователь
   */
  @PostMapping
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    UserDTO createdUser = userService.createUser(userDTO);
    return ResponseEntity.ok(createdUser);
  }

  /**
   * Получает пользователя по ID.
   *
   * @param id идентификатор пользователя
   * @return найденный пользователь
   */
  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
    UserDTO user = userService.getUserById(id);
    if (user != null) {
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * Обновляет существующего пользователя.
   *
   * @param id идентификатор пользователя
   * @param userDTO обновленные данные пользователя
   * @return обновленный пользователь
   */
  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
    UserDTO updatedUser = userService.updateUser(id, userDTO);
    if (updatedUser != null) {
      return ResponseEntity.ok(updatedUser);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * Удаляет пользователя по ID.
   *
   * @param id идентификатор пользователя
   * @return статус операции
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Получает список всех пользователей.
   *
   * @return список пользователей
   */
  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    List<UserDTO> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }
}