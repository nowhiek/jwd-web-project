package by.epam.jwd.controller.validator.util.factory;

import by.epam.jwd.controller.validator.util.UserFieldValidator;

public interface MatriculantValidatorFactory {

	UserFieldValidator<Integer> getMatriculantCertificateValidator();
}
