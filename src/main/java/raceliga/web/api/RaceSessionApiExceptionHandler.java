package raceliga.web.api;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import raceliga.dto.ApiErrorResponse;
import raceliga.services.exceptions.RaceSessionNotFoundException;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackageClasses = RaceSessionRestController.class)
public class RaceSessionApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RaceSessionNotFoundException.class)
    public ResponseEntity<Object> missing(RaceSessionNotFoundException exception, WebRequest request) {
        return response(HttpStatus.NOT_FOUND, "RACE_NOT_FOUND", "Заезд не найден", request, Map.of());
    }

    // Ловим ошибку дубликата и ошибку базы данных (например, если дважды прислали один UUID)
    @ExceptionHandler({IllegalArgumentException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Object> conflict(RuntimeException exception, WebRequest request) {
        return response(HttpStatus.CONFLICT, "DATA_CONFLICT",
                "Изменение конфликтует с существующими данными", request, Map.of());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> fields = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                fields.putIfAbsent(error.getField(), error.getDefaultMessage()));
        return response(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR",
                "Запрос содержит некорректные поля", request, fields);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body,
                                                             HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus resolved = HttpStatus.valueOf(status.value());
        return new ResponseEntity<>(error(resolved, "REQUEST_ERROR",
                "Проверьте метод, заголовки, формат и параметры запроса", request, Map.of()), headers, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> unexpected(Exception exception, WebRequest request) {
        logger.error("Непредвиденная ошибка API", exception);
        return response(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR",
                "Внутренняя ошибка сервера", request, Map.of());
    }

    private ResponseEntity<Object> response(HttpStatus status, String code, String message,
                                            WebRequest request, Map<String, String> fields) {
        return ResponseEntity.status(status).body(error(status, code, message, request, fields));
    }

    private ApiErrorResponse error(HttpStatus status, String code, String message,
                                   WebRequest request, Map<String, String> fields) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        return new ApiErrorResponse(Instant.now(), status.value(), status.getReasonPhrase(),
                code, message, path, fields);
    }
}
