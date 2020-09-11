package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;

public class CountYearStudyValidatorImpl implements FieldValidator<Integer> {

	@Override
	public boolean validate(Integer count) {
		return count >= 1 && count <= 5;
	}
}
