package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.Specialty;

public interface SpecialtyValidator extends Validator {
	
	List<String> validate(Specialty specialty);
}
