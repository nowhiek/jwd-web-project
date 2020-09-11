package by.epam.jwd.controller.validator;

import java.util.List;

import by.epam.jwd.bean.Exam;

public interface ExamValidator extends Validator {

	List<String> validate(Exam exam);
}
