package by.epam.jwd.service.exception;

public class ExamServiceException extends ServiceException {

	private static final long serialVersionUID = 2253074184508101406L;

	public ExamServiceException() {
		super();
	}

	public ExamServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExamServiceException(String message) {
		super(message);
	}

	public ExamServiceException(Throwable cause) {
		super(cause);
	}
}
