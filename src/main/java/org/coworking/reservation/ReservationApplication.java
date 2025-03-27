package org.coworking.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс Spring Boot приложения для управления бронированием.
 */
@SpringBootApplication
public class ReservationApplication {

  /**
   * Главный метод, запускающий Spring Boot приложение.
   *
   * @param args аргументы командной строки
   */
  public static void main(String[] args) {
    SpringApplication.run(ReservationApplication.class, args);
  }

}