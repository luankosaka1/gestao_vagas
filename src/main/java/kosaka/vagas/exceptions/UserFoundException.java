package kosaka.vagas.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("User found");
    }
}
