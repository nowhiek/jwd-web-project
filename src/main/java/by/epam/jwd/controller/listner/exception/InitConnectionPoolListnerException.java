package by.epam.jwd.controller.listner.exception;

public class InitConnectionPoolListnerException extends RuntimeException {

	private static final long serialVersionUID = 31L;
	
	public InitConnectionPoolListnerException() {
    }

    public InitConnectionPoolListnerException(String message) {
        super(message);
    }

    public InitConnectionPoolListnerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitConnectionPoolListnerException(Throwable cause) {
        super(cause);
    }
}
