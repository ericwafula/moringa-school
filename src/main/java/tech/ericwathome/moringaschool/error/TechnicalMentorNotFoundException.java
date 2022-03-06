package tech.ericwathome.moringaschool.error;

public class TechnicalMentorNotFoundException extends Exception {
    public TechnicalMentorNotFoundException() {
        super();
    }

    public TechnicalMentorNotFoundException(String message) {
        super(message);
    }

    public TechnicalMentorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalMentorNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TechnicalMentorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
