package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.Subject;

public interface SubjectValidator extends Validator {

	List<String> validate(Subject subject);
}
