package by.epam.jwd.controller.parameter;

public final class SessionParameter {

	public static final String ID_USER = "ID";
	public static final String NAME_USER = "LOGIN_NAME";
	public static final String ROLE_USER = "ROLE";
	public static final String MESSAGE = "MESSAGE";
	
	public static final String SPECIALTIES = "specialties";
	public static final String FACULTIES = "faculties";
	public static final String TYPE_STUDIES = "types";
	
	public static final String USER = "user";
	public static final String USERS = "users";
	public static final String USER_DETAIL = "user_detail";
	public static final String ID_USER_ADMIN = "id_user";
			
	public static final String INVALID_LOGIN = "invalid_login";
	public static final String INVALID_PASSWORD = "invalid_password";
	public static final String INVALID_REPEAT_PASSWORD = "invalid_repeat_password";
	public static final String INVALID_EMAIL = "invalid_email";
	public static final String INVALID_NAME = "invalid_name";
	public static final String INVALID_SURNAME = "invalid_surname";
	public static final String INVALID_BIRTHDAY = "invalid_birthday";
	public static final String INVALID_PASSPORT = "invalid_passport";
	public static final String INVALID_CERTIFICATE = "invalid_certificate";
	public static final String USER_IS_BLOCKED = "user_is_blocked";
	
	public static final String USER_ALREADY_EXIST = "user_already_exist";
	public static final String USER_NOT_EXIST = "user_not_exist";
	public static final String SUCCESS_CHANGE_PASSWORD = "success_change_password";
	public static final String UNSUCCESS_CHANGE_PASSWORD = "unsuccess_change_password";
	public static final String SUCCESS_UPDATE_USER_DETAIL = "succes_update_user_detail";
	public static final String UNSUCCESS_UPDATE_USER_DETAIL = "unsucces_update_user_detail";
	public static final String SUCCESS_ADD_MATRICULANT = "success_add_matriculant";
	public static final String UNSUCCESS_ADD_MATRICULANT = "unsuccess_add_matriculant";
	public static final String SUCCESS_BAN_USER = "success_ban_user";
	public static final String UNSUCCESS_BAN_USER = "unsuccess_ban_user";	
	
	public static final String INVALID_CHANGE_OLD_PASSWORD = "invalid_change_old_password";
	public static final String INVALID_CHANGE_NEW_PASSWORD = "invalid_change_new_password";
	public static final String INVALID_NOT_EQUALS_PASSWORD = "invalid_not_equals_password";
	
	public static final String EMPTY_LOGIN = "empty_login";
	public static final String EMPTY_PASSWORD = "empty_password";
	public static final String EMPTY_SPECIALTIES = "empty_specialties";
	public static final String EMPTY_SPECIALTY = "empty_specialty";
	public static final String EMPTY_USERS = "empty_users";
	
	private SessionParameter() {
	}
}
