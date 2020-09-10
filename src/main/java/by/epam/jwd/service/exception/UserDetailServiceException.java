package by.epam.jwd.service.exception;

public class UserDetailServiceException extends ServiceException {

	private static final long serialVersionUID = 1762771169632193150L;

	public UserDetailServiceException() {
		super();
	}

	public UserDetailServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDetailServiceException(String message) {
		super(message);
	}

	public UserDetailServiceException(Throwable cause) {
		super(cause);
	}
}
