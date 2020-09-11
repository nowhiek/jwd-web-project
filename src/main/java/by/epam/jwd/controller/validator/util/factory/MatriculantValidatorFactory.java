package by.epam.jwd.controller.validator.util.factory;

import by.epam.jwd.controller.validator.util.FieldValidator;

public interface MatriculantValidatorFactory {

	FieldValidator<Integer> getMatriculantCertificateValidator();
	FieldValidator<String> getMatriculantSpecialtyNameValidator();
}
