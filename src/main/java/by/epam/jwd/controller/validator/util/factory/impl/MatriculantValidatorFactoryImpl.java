package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.factory.MatriculantValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.MatriculantCertificateValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.SpecialtyNameValidatorImpl;

public class MatriculantValidatorFactoryImpl implements MatriculantValidatorFactory {

	private static final MatriculantValidatorFactoryImpl instance = new MatriculantValidatorFactoryImpl();
	
	private static final FieldValidator<Integer> matriculantCertificateValidator = new MatriculantCertificateValidatorImpl();
	private static final FieldValidator<String> matriculantSpecialtyNameValidator = new SpecialtyNameValidatorImpl();
	
	private MatriculantValidatorFactoryImpl() {
	}
	
	public static MatriculantValidatorFactoryImpl getInstance() {
		return instance;
	}

	@Override
	public FieldValidator<Integer> getMatriculantCertificateValidator() {
		return matriculantCertificateValidator;
	}

	@Override
	public FieldValidator<String> getMatriculantSpecialtyNameValidator() {
		return matriculantSpecialtyNameValidator;
	}
}
