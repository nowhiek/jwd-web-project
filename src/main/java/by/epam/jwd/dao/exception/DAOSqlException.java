package by.epam.jwd.dao.exception;

import java.io.Serializable;

public class DAOSqlException extends DAOException implements Serializable {

	private static final long serialVersionUID = -4352761346756588393L;

	public DAOSqlException() {
		super();
	}

	public DAOSqlException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOSqlException(String message) {
		super(message);
	}

	public DAOSqlException(Throwable cause) {
		super(cause);
	}
}
