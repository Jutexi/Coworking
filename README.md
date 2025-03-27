# Coworking Management System

## Описание

Coworking Management System — это веб-приложение для управления бронированием рабочих мест в коворкингах. Пользователи могут просматривать доступные бронирования и фильтровать их по параметрам.

## Стек технологий

- Java 17
- Spring Boot

### Клонирование репозитория

```bash
git clone https://github.com/Jutexi/Coworking.git
cd Carsharing
```
## Использование

### Эндпоинты API

Приложение предоставляет следующие RESTful эндпоинты для работы с бронированиями:

1. **Получить список всех бронирований**

    - **Метод**: `GET`
    - **URL**: `/api/reservations/all`
    - **Пример запроса**:
      ```bash
      curl -X GET "http://localhost:8080/api/reservations/all"
      ```
    - **Ответ**: Возвращает список всех бронирований в формате JSON:
      ```json
      [
        {
          "id": 0,
          "coworkingSpace": "Main Hall",
          "date": "2025-03-18",
          "timeSlot": "09:00-12:00"
        },
        {
          "id": 1,
          "coworkingSpace": "Creative Space",
          "date": "2025-03-18",
          "timeSlot": "13:00-16:00"
        },
        {
          "id": 2,
          "coworkingSpace": "Conference Room",
          "date": "2025-03-19",
          "timeSlot": "10:00-12:00"
        }
      ]
      ```

2. **Получить бронирование по ID**

    - **Метод**: `GET`
    - **URL**: `/api/reservations/{id}`
    - **Параметры пути**:
        - `id` — уникальный идентификатор автомобиля.
    - **Пример запроса**:
      ```bash
      curl -X GET "http://localhost:8080/api/reservations/1"
      ```
    - **Ответ**:
      ```json
      {
        "id": 0,
        "coworkingSpace": "Main Hall",
        "date": "2025-03-18",
        "timeSlot": "09:00-12:00"
      }
      ```
3. **Фильтрация бронирований по дате и/или названию коворкинга**
    - **Метод**: `GET`
    - **URL**: `/api/reservations/filter`
    - **Параметры запроса:**:
        - `date`(необязательный) — дата бронирования.
        - `space`(необязательный) — дата бронирования
    - **Пример запроса**:
      ```bash
      curl -X GET "http://localhost:8080/api/reservations/filter?date=2025-03-18&space=Main Hall"
      ```
    - **Ответ**:
      ```json
      {
        "id": 0,
        "coworkingSpace": "Main Hall",
        "date": "2025-03-18",
        "timeSlot": "09:00-12:00"
      }
      ```

