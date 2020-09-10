package by.epam.jwd.controller.validator.util.factory;

import by.epam.jwd.controller.validator.util.FieldValidator;

public interface SpecialtyHasSubjectValidatorFactory {	

	FieldValidator<String> getFacultyNameValidator();
	FieldValidator<String> getSubjectNameValidator();
}
