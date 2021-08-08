package challenge.exception;

public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}