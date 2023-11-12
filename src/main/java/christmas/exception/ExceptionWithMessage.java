package christmas.exception;

public class ExceptionWithMessage extends IllegalArgumentException {

    public ExceptionWithMessage(String message) {
        super(message);
        System.out.println(message);
    }
}
