package by.epam.jwd.controller.validator.util;

public final class Regex {
	
	public static final String USER_LOGIN_REGEX = "^[a-zA-Z]+([-_]?[a-zA-Z0-9]+){0,2}$";
	public static final String USER_PASSWORD_REGEX = "(?=^.{8,16}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
	public static final String USER_EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
	public static final String USER_FULL_NAME_REGEX = "[а-яa-zА-ЯA-Z-]{1,20}$";
	public static final String USER_BIRTHDAY_REGEX = "(0[1-9]|[12][0-9]|3[01])[-.](0[1-9]|1[012])[-.](19|20)\\d\\d$";
	public static final String USER_PASSPORT_REGEX = "^\\w{2}\\d{7}$";
	
	private Regex() {
	}
}
