package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.Faculty;

public interface FacultyValidator extends Validator {

	List<String> validate(Faculty faculty);
}
