package exceptions;

public class LimitOfReconnectionsException extends Exception{
    public LimitOfReconnectionsException() {
        super("Превышен лимит переподключений!!!");
    }

    public LimitOfReconnectionsException(String message) {
        super(message);
    }
}
