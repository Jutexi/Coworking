package org.coworking.reservation.controller;

import java.util.List;
import org.coworking.reservation.dto.CoworkingSpaceDto;
import org.coworking.reservation.service.CoworkingSpaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coworking-spaces")
public class CoworkingSpaceController {

  private final CoworkingSpaceService coworkingSpaceService;

  public CoworkingSpaceController(CoworkingSpaceService coworkingSpaceService) {
    this.coworkingSpaceService = coworkingSpaceService;
  }

  /**
   * Создает новый коворкинг.
   *
   * @param coworkingSpaceDTO DTO коворкинга
   * @return созданный коворкинг
   */
  @PostMapping
  public ResponseEntity<CoworkingSpaceDto> createCoworkingSpace(@RequestBody CoworkingSpaceDto coworkingSpaceDTO) {
    CoworkingSpaceDto createdCoworkingSpace = coworkingSpaceService.createCoworkingSpace(coworkingSpaceDTO);
    return ResponseEntity.ok(createdCoworkingSpace);
  }
  /**
   * Получает коворкинг по ID.
   *
   * @param id идентификатор коворкинга
   * @return найденный коворкинг
   */
  @GetMapping("/{id}")
  public ResponseEntity<CoworkingSpaceDto> getCoworkingSpaceById(@PathVariable Integer id) {
    CoworkingSpaceDto coworkingSpace = coworkingSpaceService.getCoworkingSpaceById(id);
    if (coworkingSpace != null) {
      return ResponseEntity.ok(coworkingSpace);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * Обновляет существующий коворкинг.
   *
   * @param id идентификатор коворкинга
   * @param coworkingSpaceDTO обновленные данные коворкинга
   * @return обновленный коворкинг
   */
  @PutMapping("/{id}")
  public ResponseEntity<CoworkingSpaceDto> updateCoworkingSpace(@PathVariable Integer id, @RequestBody CoworkingSpaceDto coworkingSpaceDTO) {
    CoworkingSpaceDto updatedCoworkingSpace = coworkingSpaceService.updateCoworkingSpace(id, coworkingSpaceDTO);
    if (updatedCoworkingSpace != null) {
      return ResponseEntity.ok(updatedCoworkingSpace);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * Удаляет коворкинг по ID.
   *
   * @param id идентификатор коворкинга
   * @return статус операции
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCoworkingSpace(@PathVariable Integer id) {
    coworkingSpaceService.deleteCoworkingSpace(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Получает список всех коворкингов.
   *
   * @return список коворкингов
   */
  @GetMapping
  public ResponseEntity<List<CoworkingSpaceDto>> getAllCoworkingSpaces() {
    List<CoworkingSpaceDto> coworkingSpaces = coworkingSpaceService.getAllCoworkingSpaces();
    return ResponseEntity.ok(coworkingSpaces);
  }
}