package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Exam;
import by.epam.jwd.bean.SpecialtyHasSubject;
import by.epam.jwd.bean.Subject;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.ExamValidator;
import by.epam.jwd.controller.validator.util.factory.ExamValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ExamValidatorImpl implements ExamValidator {

	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private ExamValidatorFactory examValidator = validatorFactory.getExamValidatorFactory();
	
	@Override
	public List<String> validate(Exam exam) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		List<String> validation = new ArrayList<String>();
		
		try {
			SpecialtyHasSubject has = serviceFactory.getSpecialtyHasSubjectService().findSpecialtyHasSubjectById(exam.getIdSpecialtyHasSubject());
			Subject subject = serviceFactory.getSubjectService().findSubjectById(has.getIdSubject());
			
			if (!examValidator.getSubjectNameValidator().validate(subject.getSubjectName())) {
				validation.add(ValidateParameter.INVALID_SUBJECT_NAME);
			}
			
			if (!examValidator.getExamMarkValidator().validate(exam.getResultMark())) {
				validation.add(ValidateParameter.INVALID_MARK);
			}
		} catch(ServiceException e) {
			e.printStackTrace();
		}
		
		return validation;
	}
}
