package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.UserFieldValidator;
import by.epam.jwd.controller.validator.util.factory.MatriculantValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.MatriculantCertificateValidatorImpl;

public class MatriculantValidatorFactoryImpl implements MatriculantValidatorFactory {

	private static final MatriculantValidatorFactoryImpl instance = new MatriculantValidatorFactoryImpl();
	
	private static final UserFieldValidator<Integer> matriculantCertificateValidator = new MatriculantCertificateValidatorImpl();

	private MatriculantValidatorFactoryImpl() {
	}
	
	public static MatriculantValidatorFactoryImpl getInstance() {
		return instance;
	}

	@Override
	public UserFieldValidator<Integer> getMatriculantCertificateValidator() {
		return matriculantCertificateValidator;
	}
}
