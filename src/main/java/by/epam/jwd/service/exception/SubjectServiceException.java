package by.epam.jwd.service.exception;

public class SubjectServiceException extends ServiceException {

	private static final long serialVersionUID = 8512070659005085243L;

	public SubjectServiceException() {
		super();
	}

	public SubjectServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SubjectServiceException(String message) {
		super(message);
	}

	public SubjectServiceException(Throwable cause) {
		super(cause);
	}
}
