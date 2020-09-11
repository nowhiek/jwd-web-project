package by.epam.jwd.service.exception;

public class UserExistServiceException extends ServiceException {

	private static final long serialVersionUID = -1389563885821544572L;

	public UserExistServiceException() {
		super();
	}

	public UserExistServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserExistServiceException(String message) {
		super(message);
	}

	public UserExistServiceException(Throwable cause) {
		super(cause);
	}
}
