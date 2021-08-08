package challenge.exception;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFileException(String message) {
        super(message);
    }
}