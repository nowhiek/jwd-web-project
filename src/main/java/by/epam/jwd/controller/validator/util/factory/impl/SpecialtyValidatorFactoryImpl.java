package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.factory.SpecialtyValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.CountYearStudyValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.FacultyNameValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.PlanCountValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.QualificationValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.SpecialtyNameValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.TypeStudyNameValidatorImpl;

public class SpecialtyValidatorFactoryImpl  implements SpecialtyValidatorFactory {

	private static final SpecialtyValidatorFactoryImpl instance = new SpecialtyValidatorFactoryImpl();
	
	private static final FieldValidator<String> facultyNameValidator = new FacultyNameValidatorImpl();
	private static final FieldValidator<String> typeStudyNameValidator = new TypeStudyNameValidatorImpl();
	private static final FieldValidator<String> specialtyNameValidator = new SpecialtyNameValidatorImpl();
	private static final FieldValidator<String> qualificationValidator= new QualificationValidatorImpl();
	private static final FieldValidator<Integer> planCountValidator = new PlanCountValidatorImpl();
	private static final FieldValidator<Integer> countYearStudyValidator = new CountYearStudyValidatorImpl();
	
	private SpecialtyValidatorFactoryImpl() {
	}
	
	public static SpecialtyValidatorFactoryImpl getInstance() {
		return instance;
	}
	
	@Override
	public FieldValidator<String> getFacultyNameValidator() {
		return facultyNameValidator;
	}

	@Override
	public FieldValidator<String> getTypeStudyNameValidator() {
		return typeStudyNameValidator;
	}

	@Override
	public FieldValidator<String> getSpecialtyNameValidator() {
		return specialtyNameValidator;
	}

	@Override
	public FieldValidator<String> getQualificationValidator() {
		return qualificationValidator;
	}

	@Override
	public FieldValidator<Integer> getCountPlacesValidator() {
		return planCountValidator;
	}

	@Override
	public FieldValidator<Integer> getCountYearStudyValidator() {
		return countYearStudyValidator;
	}
}