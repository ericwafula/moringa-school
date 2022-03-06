package tech.ericwathome.moringaschool.error;

public class EmptyParameterException extends Exception {
    public EmptyParameterException() {
        super();
    }

    public EmptyParameterException(String message) {
        super(message);
    }

    public EmptyParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyParameterException(Throwable cause) {
        super(cause);
    }

    protected EmptyParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
