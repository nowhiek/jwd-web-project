package by.epam.jwd.controller.validator.util.factory;

import by.epam.jwd.controller.validator.util.FieldValidator;

public interface SpecialtyValidatorFactory {

	FieldValidator<String> getTypeStudyNameValidator();
	FieldValidator<String> getFacultyNameValidator();
	FieldValidator<String> getSpecialtyNameValidator();
	FieldValidator<String> getQualificationValidator();
	FieldValidator<Integer> getCountPlacesValidator();
	FieldValidator<Integer> getCountYearStudyValidator();	
}
