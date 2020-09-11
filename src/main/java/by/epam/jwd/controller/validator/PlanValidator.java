package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.Plan;

public interface PlanValidator extends Validator {

	List<String> validate(Plan plan);
}
