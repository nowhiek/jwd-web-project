package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.UserDetail;

public interface UserDetailValidator {
	
	List<String> validate(UserDetail detail);
}
