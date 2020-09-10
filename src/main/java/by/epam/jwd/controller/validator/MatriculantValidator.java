package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.Matriculant;

public interface MatriculantValidator extends Validator {

	List<String> validate(Matriculant matriculant);
}
