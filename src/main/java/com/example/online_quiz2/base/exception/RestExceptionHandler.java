package com.example.online_quiz2.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serializable;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler implements Serializable {


    private static final long serialVersionUID = -316835666759959160L;

    @ExceptionHandler(value = BadRequestRuntimeException.class)
    public ResponseEntity<ExceptionData> handleBadRequestException(BadRequestRuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(
                        new ExceptionData(
                                ex.getMessage(),
                                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        )
                );
    }


    @ExceptionHandler(value = UserNotActiveRuntimeException.class)
    public ResponseEntity<ExceptionData> handleUserNotActiveRuntimeException(UserNotActiveRuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(
                        new ExceptionData(
                                ex.getMessage(),
                                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        )
                );
    }

    @ExceptionHandler(value = EntityNotFoundRuntimeException.class)
    public ResponseEntity<ExceptionData> handleEntityNotFoundException(EntityNotFoundRuntimeException ex) {
        return new ResponseEntity<>(
                new ExceptionData(
                        ex.getMessage(),
                        LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = DTONotFoundRuntimeException.class)
    public ResponseEntity<ExceptionData> handleDtoNotFoundException(DTONotFoundRuntimeException ex) {
        return new ResponseEntity<>(
                new ExceptionData(
                        ex.getMessage(),
                        LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ExceptionData> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(
                new ExceptionData(
                        ex.getMessage(),
                        LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> getMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST);

        //Get all errors
        List<ErrorDTO> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ErrorDTO implements Serializable {

        private static final long serialVersionUID = -5655404318193052723L;
        private String field;
        private String message;
    }

}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class ExceptionData implements Serializable {


    private static final long serialVersionUID = -4211251717520290198L;

    private String message;
    private String date;
}
