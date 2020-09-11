package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Plan;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.SpecialtyValidator;
import by.epam.jwd.controller.validator.util.factory.SpecialtyValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class SpecialtyValidatorImpl implements SpecialtyValidator {

	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private SpecialtyValidatorFactory specialtyValidator = validatorFactory.getSpecialtyValidatorFactory();
	
	@Override
	public List<String> validate(Specialty specilaty) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		List<String> validation = new ArrayList<String>();
		
		try {
			TypeStudy type = serviceFactory.getTypeStudyService().findTypeStudyById(specilaty.getIdTypeStudy());
			Faculty faculty = serviceFactory.getFacultyService().findFacultyById(specilaty.getIdFaculty());
			Plan plan = serviceFactory.getPlanService().findPlanById(specilaty.getIdPlan());
			
			if (!specialtyValidator.getTypeStudyNameValidator().validate(type.getStudyName())) {
				validation.add(ValidateParameter.INVALID_TYPE_STUDY_NAME);
			}
			
			if (!specialtyValidator.getFacultyNameValidator().validate(faculty.getFacultyName())) {
				validation.add(ValidateParameter.INVALID_FACULTY_NAME);
			}
			
			if (!specialtyValidator.getSpecialtyNameValidator().validate(specilaty.getSpecialtyName())) {
				validation.add(ValidateParameter.INVALID_SPECIALTY_NAME);
			}
			
			if (!specialtyValidator.getQualificationValidator().validate(specilaty.getQualification())) {
				validation.add(ValidateParameter.INVALID_QUALIFICATION);
			}
			
			if (!specialtyValidator.getCountPlacesValidator().validate(plan.getCountPlaces())) {
				validation.add(ValidateParameter.INVALID_COUNT_PLACES);
			}
			
			if (!specialtyValidator.getCountYearStudyValidator().validate(specilaty.getCountYearStudy())) {
				validation.add(ValidateParameter.INVALID_COUNT_YEAR_STUDY);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return validation;
	}
}

