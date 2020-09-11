package by.epam.jwd.dao.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 7025374177680793457L;

	public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
