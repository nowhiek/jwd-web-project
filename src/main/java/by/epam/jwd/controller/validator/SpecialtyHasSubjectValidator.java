package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.SpecialtyHasSubject;

public interface SpecialtyHasSubjectValidator extends Validator {

	List<String> validate(SpecialtyHasSubject has);
}
