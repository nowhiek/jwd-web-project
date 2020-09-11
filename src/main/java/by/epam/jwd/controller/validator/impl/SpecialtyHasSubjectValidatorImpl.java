package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.SpecialtyHasSubject;
import by.epam.jwd.bean.Subject;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.SpecialtyHasSubjectValidator;
import by.epam.jwd.controller.validator.util.factory.SpecialtyHasSubjectValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class SpecialtyHasSubjectValidatorImpl implements SpecialtyHasSubjectValidator {

	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private SpecialtyHasSubjectValidatorFactory specialtyHasSubjectValidator = validatorFactory.getSpecialtyHasSubjectValidatorFactory();
	
	@Override
	public List<String> validate(SpecialtyHasSubject has) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		List<String> validation = new ArrayList<String>();

		try {
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(has.getIdSpecialty());
			Subject subject = serviceFactory.getSubjectService().findSubjectById(has.getIdSubject());
			
			if (!specialtyHasSubjectValidator.getFacultyNameValidator().validate(specialty.getSpecialtyName())) {
				validation.add(ValidateParameter.INVALID_FACULTY_NAME);
			}
			
			if (!specialtyHasSubjectValidator.getSubjectNameValidator().validate(subject.getSubjectName())){
				validation.add(ValidateParameter.INVALID_SUBJECT_NAME);
			}
		} catch (ServiceException e) {
			
		}
		
		return validation;
	}
}
