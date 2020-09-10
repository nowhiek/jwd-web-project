package by.epam.jwd.controller.validator.util.factory;

import by.epam.jwd.controller.validator.util.factory.impl.ExamValidatorFactoryImpl;
import by.epam.jwd.controller.validator.util.factory.impl.FacultyValidatorFactoryImpl;
import by.epam.jwd.controller.validator.util.factory.impl.MatriculantValidatorFactoryImpl;
import by.epam.jwd.controller.validator.util.factory.impl.PlanValidatorFactoryImpl;
import by.epam.jwd.controller.validator.util.factory.impl.SpecialtyHasSubjectValidatorFactoryImpl;
import by.epam.jwd.controller.validator.util.factory.impl.SpecialtyValidatorFactoryImpl;
import by.epam.jwd.controller.validator.util.factory.impl.SubjectValidatorFactoryImpl;
import by.epam.jwd.controller.validator.util.factory.impl.TypeStudyValidatorFactoryImpl;
import by.epam.jwd.controller.validator.util.factory.impl.UserDetailValidatorFacotyImpl;
import by.epam.jwd.controller.validator.util.factory.impl.UserValidatorFactoryImpl;

public class ValidatorFactory {

	private static final ValidatorFactory instance = new ValidatorFactory();
	
	private static final UserValidatorFactory userValidatorFactory = UserValidatorFactoryImpl.getInstance();
	private static final UserDetailValidatorFactory userDetailValidatorFactory = UserDetailValidatorFacotyImpl.getInstance();
	private static final MatriculantValidatorFactory matriculantValidatorFactory = MatriculantValidatorFactoryImpl.getInstance();
	private static final FacultyValidatorFactory facultyValidatorFactory = FacultyValidatorFactoryImpl.getInstance();
	private static final TypeStudyValidatorFactory typeStudyValidatorFactory = TypeStudyValidatorFactoryImpl.getInstance();
	private static final SubjectValidatorFactory subjectValidatorFactory = SubjectValidatorFactoryImpl.getInstance();
	private static final PlanValidatorFactory planValidatorFactory = PlanValidatorFactoryImpl.getInstance();
	private static final SpecialtyHasSubjectValidatorFactory specialtyHasSubjectValidatorFactory = SpecialtyHasSubjectValidatorFactoryImpl.getInstance();
	private static final SpecialtyValidatorFactory specialtyValidatorFactory = SpecialtyValidatorFactoryImpl.getInstance();
	private static final ExamValidatorFactory examValidatorFactory = ExamValidatorFactoryImpl.getInstance();
	
	private ValidatorFactory() {
	}
	
	public static ValidatorFactory getInstance() {
		return instance;
	}
	
	public UserValidatorFactory getUserValidatorFactory() {
		return userValidatorFactory;
	}
	
	public UserDetailValidatorFactory getUserDetailValidatorFactory() {
		return userDetailValidatorFactory;
	}
	
	public MatriculantValidatorFactory getMatriculantValidatorFactory() {
		return matriculantValidatorFactory;
	}
	
	public FacultyValidatorFactory getFacultyValidatorFactory() {
		return facultyValidatorFactory;
	}
	
	public TypeStudyValidatorFactory getTypeStudyValidatorFactory() {
		return typeStudyValidatorFactory;
	}
	
	public SubjectValidatorFactory getSubjectValidatorFactory() {
		return subjectValidatorFactory;
	}
	
	public PlanValidatorFactory getPlanValidatorFactory() {
		return planValidatorFactory;
	}
	
	public SpecialtyHasSubjectValidatorFactory getSpecialtyHasSubjectValidatorFactory() {
		return specialtyHasSubjectValidatorFactory;
	}
	
	public SpecialtyValidatorFactory getSpecialtyValidatorFactory() {
		return specialtyValidatorFactory;
	}
	
	public ExamValidatorFactory getExamValidatorFactory() {
		return examValidatorFactory;
	}
}
