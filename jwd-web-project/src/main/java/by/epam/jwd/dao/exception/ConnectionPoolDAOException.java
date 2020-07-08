package by.epam.jwd.dao.exception;

public class ConnectionPoolDAOException extends DAOException {

	private static final long serialVersionUID = 1L;

	public ConnectionPoolDAOException() {
    }

    public ConnectionPoolDAOException(String message) {
        super(message);
    }

    public ConnectionPoolDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolDAOException(Throwable cause) {
        super(cause);
    }
}
