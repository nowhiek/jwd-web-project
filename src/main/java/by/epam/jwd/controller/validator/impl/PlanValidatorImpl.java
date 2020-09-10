package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Plan;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.PlanValidator;
import by.epam.jwd.controller.validator.util.factory.PlanValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;

public class PlanValidatorImpl implements PlanValidator {

	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private PlanValidatorFactory planValidator = validatorFactory.getPlanValidatorFactory();
	
	@Override
	public List<String> validate(Plan plan) {
		List<String> validation = new ArrayList<String>();
		
		if (!planValidator.getPlanCountValidator().validate(plan.getCountPlaces())) {
			validation.add(ValidateParameter.INVALID_COUNT_PLACES);
		}
		
		return validation;
	}
}