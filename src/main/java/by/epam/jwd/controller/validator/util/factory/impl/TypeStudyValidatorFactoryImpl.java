package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.factory.TypeStudyValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.TypeStudyNameValidatorImpl;

public class TypeStudyValidatorFactoryImpl implements TypeStudyValidatorFactory {

	private static final TypeStudyValidatorFactoryImpl instance = new TypeStudyValidatorFactoryImpl();
	
	private static final FieldValidator<String> typeStudyValidator = new TypeStudyNameValidatorImpl();
	
	
	private TypeStudyValidatorFactoryImpl() {
	}
	
	public static TypeStudyValidatorFactoryImpl getInstance() {
		return instance;
	}
	
	@Override
	public FieldValidator<String> getTypeStudyValidator() {
		return typeStudyValidator;
	}
}
