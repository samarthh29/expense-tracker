package expense.user_service.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<String> handleValidationExceptions(UserValidationException ex) {
        return ResponseEntity.badRequest().body( "User Validation Error");
    }
}
