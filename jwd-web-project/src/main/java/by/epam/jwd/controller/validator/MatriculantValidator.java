package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.Matriculant;

public interface MatriculantValidator {

	List<String> validate(Matriculant matriculant);
}
