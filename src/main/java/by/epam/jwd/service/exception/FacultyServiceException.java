package by.epam.jwd.service.exception;

public class FacultyServiceException extends ServiceException {

	private static final long serialVersionUID = 1311330363747459629L;

	public FacultyServiceException() {
		super();
	}

	public FacultyServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public FacultyServiceException(String message) {
		super(message);
	}

	public FacultyServiceException(Throwable cause) {
		super(cause);
	}
}
