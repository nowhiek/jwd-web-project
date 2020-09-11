package by.epam.jwd.controller.validator.util.factory;

import by.epam.jwd.controller.validator.util.FieldValidator;

public interface ExamValidatorFactory {

	FieldValidator<Integer> getExamMarkValidator();
	FieldValidator<String> getSubjectNameValidator();
}
