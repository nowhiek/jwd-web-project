package by.epam.jwd.service.exception;

public class UserServiceException extends ServiceException {

	private static final long serialVersionUID = -1947563087826822682L;
	
	public UserServiceException() {
		super();
	}

	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(Throwable cause) {
		super(cause);
	}
}
