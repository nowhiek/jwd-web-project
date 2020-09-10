package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.factory.PlanValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.PlanCountValidatorImpl;

public class PlanValidatorFactoryImpl implements PlanValidatorFactory {

	private static final PlanValidatorFactoryImpl instance = new PlanValidatorFactoryImpl();
	
	private static final FieldValidator<Integer> planCountValidator = new PlanCountValidatorImpl();
	
	
	private PlanValidatorFactoryImpl() {
	}
	
	public static PlanValidatorFactoryImpl getInstance() {
		return instance;
	}

	@Override
	public FieldValidator<Integer> getPlanCountValidator() {
		return planCountValidator;
	}
}

