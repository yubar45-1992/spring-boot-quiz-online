package com.example.online_quiz2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionData> handleBadRequestException(SQLIntegrityConstraintViolationException ex) {
        String message = null;
        if (ex.getMessage().contains("user_uk_username")) {
            message = "this username is already taken";
        }
        return ResponseEntity.badRequest().body(

                new ExceptionData(message != null ? message : ex.getMessage(),
                        LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        );

    }


}


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class ExceptionData implements Serializable {
    private String message;
    private String date;

}
