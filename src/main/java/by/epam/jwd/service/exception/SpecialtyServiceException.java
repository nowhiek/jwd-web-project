package by.epam.jwd.service.exception;

public class SpecialtyServiceException extends ServiceException {

	private static final long serialVersionUID = 5096628596722689944L;

	public SpecialtyServiceException() {
		super();
	}

	public SpecialtyServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpecialtyServiceException(String message) {
		super(message);
	}

	public SpecialtyServiceException(Throwable cause) {
		super(cause);
	}
}
