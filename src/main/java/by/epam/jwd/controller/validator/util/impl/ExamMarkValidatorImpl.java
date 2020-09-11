package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;

public class ExamMarkValidatorImpl implements FieldValidator<Integer> {

	@Override
	public boolean validate(Integer mark) {
		return mark >= 0 && mark <= 10;
	}

}
