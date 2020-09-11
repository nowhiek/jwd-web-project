package by.epam.jwd.service.exception;

public class ResultServiceException extends ServiceException {

	private static final long serialVersionUID = -8395487135921133495L;

	public ResultServiceException() {
		super();
	}

	public ResultServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResultServiceException(String message) {
		super(message);
	}

	public ResultServiceException(Throwable cause) {
		super(cause);
	}
}
