# Spring Boot Application

## Opis

Aplikacja Spring Boot z endpointem, który zwraca listę przedmiotów o największej liczbie wspólnych liter między nazwą
przedmiotu a imieniem i nazwiskiem prowadzącego. Aplikacja korzysta z bazy danych PostgreSQL uruchamianej za pomocą
Docker Compose.

---

## Funkcjonalności

- **`GET /api/subjects/top-common-letters`**: Endpoint zwracający listę przedmiotów według liczby wspólnych liter między
  nazwą przedmiotu a danymi prowadzącego.
- Integracja z bazą danych PostgreSQL w kontenerze Docker.
- Możliwość ustawienia limitu wyników za pomocą parametru zapytania (`limit`).

---

## Wymagania wstępne

1. Zainstalowane narzędzia:
    - **Docker** i **Docker Compose**.
    - **Java 17+**.
2. Opcjonalnie:
    - **Postman** lub inne narzędzie do testowania API.

---

## Instalacja i uruchomienie

### 1. Uruchomienie bazy danych PostgreSQL z Docker Compose

   ```bash
   docker-compose up -d
   ```

### 2. Uruchomienie aplikacji Spring Boot

   ```bash
   ./mvnw spring-boot:run
   ```

## Testowanie API

Endpoint API można przetestować, wykonując zapytanie do adresu:

   ```bash
   GET http://localhost:8080/api/subjects/top-common-letters?limit=2
   ```

#### Parametry:

limit (opcjonalny): Liczba wyników do zwrócenia (domyślnie 10).

#### Przykład odpowiedzi:

```bash
[
  {
    "name": "Analiza Matematyczna",
    "lecturerFullName": "Anna Nowak"
  },
  {
    "name": "Chemia Fizyczna",
    "lecturerFullName": "Adam Wysocki"
  }
]
```

#### Testy jednostkowe

```bash 
./mvnw test
```
## Uwagi
Porty 5432 i 8080 muszą być dostępne.

