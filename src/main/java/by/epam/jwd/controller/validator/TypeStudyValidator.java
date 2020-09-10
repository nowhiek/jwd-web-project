package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.TypeStudy;

public interface TypeStudyValidator extends Validator {

	List<String> validate(TypeStudy type);
}
