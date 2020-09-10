package by.epam.jwd.controller.validator.util.impl;

import java.sql.Date;

import by.epam.jwd.controller.validator.util.Regex;
import by.epam.jwd.controller.validator.util.FieldValidator;

public class UserDetailBirthdayValidatorImpl implements FieldValidator<Date> {

	@Override
	public boolean validate(Date birthday) {
		String day = null;
		String month = null;
		String year = null;
		
		StringBuilder sb = new StringBuilder();
		
		if (birthday != null) {
			String[] split = birthday.toString().split("-");
			
			day = split[2];
			month = split[1];
			year = split[0];
			
			sb.append(day + "." + month + "." + year);
		}

		return sb.toString().matches(Regex.USER_BIRTHDAY_REGEX);
	}
}
