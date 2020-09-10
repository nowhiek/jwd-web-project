package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.User;

public interface UserValidator extends Validator {

	List<String> validate(User user);
}
