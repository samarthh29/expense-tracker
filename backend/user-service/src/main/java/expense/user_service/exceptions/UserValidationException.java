package expense.user_service.exceptions;


public class UserValidationException extends RuntimeException {
    public UserValidationException(String message) {
        super(message);
    }

}

