package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.User;

public interface UserValidator {

	List<String> validate(User user);
}
