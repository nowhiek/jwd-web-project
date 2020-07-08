package by.epam.jwd.dao.connectionpool.exception;

public class ConnectionPoolException extends Exception {
	
	private static final long serialVersionUID = 3L;

	public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
