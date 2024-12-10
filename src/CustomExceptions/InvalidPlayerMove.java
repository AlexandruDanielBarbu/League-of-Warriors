package CustomExceptions;

public class InvalidPlayerMove extends RuntimeException {
    public InvalidPlayerMove(String message) {
        super(message);
    }
}
