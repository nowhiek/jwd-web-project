package by.epam.jwd.service.exception;

public class PlanServiceException extends ServiceException {

	private static final long serialVersionUID = 2292178996501048889L;

	public PlanServiceException() {
		super();
	}

	public PlanServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlanServiceException(String message) {
		super(message);
	}

	public PlanServiceException(Throwable cause) {
		super(cause);
	}
}
