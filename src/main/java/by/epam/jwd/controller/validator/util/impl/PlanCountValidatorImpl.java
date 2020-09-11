package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;

public class PlanCountValidatorImpl implements FieldValidator<Integer> {

	@Override
	public boolean validate(Integer count) {
		return count >= 0 && count < 1000;
	}
}
