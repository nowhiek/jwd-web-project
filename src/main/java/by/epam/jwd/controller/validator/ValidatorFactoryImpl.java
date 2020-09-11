package by.epam.jwd.controller.validator;

import by.epam.jwd.controller.validator.impl.ExamValidatorImpl;
import by.epam.jwd.controller.validator.impl.FacultyValidatorImpl;
import by.epam.jwd.controller.validator.impl.MatriculantValidatorImpl;
import by.epam.jwd.controller.validator.impl.PlanValidatorImpl;
import by.epam.jwd.controller.validator.impl.SpecialtyHasSubjectValidatorImpl;
import by.epam.jwd.controller.validator.impl.SpecialtyValidatorImpl;
import by.epam.jwd.controller.validator.impl.SubjectValidatorImpl;
import by.epam.jwd.controller.validator.impl.TypeStudyValidatorImpl;
import by.epam.jwd.controller.validator.impl.UserDetailValidatorImpl;
import by.epam.jwd.controller.validator.impl.UserValidatorImpl;

public class ValidatorFactoryImpl {

	private static final ValidatorFactoryImpl instance = new ValidatorFactoryImpl();
	
	private final UserValidator userValidator = new UserValidatorImpl();
	private final UserDetailValidator userDetailValidator = new UserDetailValidatorImpl();
	private final MatriculantValidator matriculantValidator = new MatriculantValidatorImpl();
	private final FacultyValidator facultyValidator = new FacultyValidatorImpl();
	private final TypeStudyValidator typeStudyValidator = new TypeStudyValidatorImpl();
	private final SubjectValidator subjectValidator = new SubjectValidatorImpl();
	private final PlanValidator planValidator = new PlanValidatorImpl();
	private final SpecialtyValidator specialtyValidator = new SpecialtyValidatorImpl();
	private final SpecialtyHasSubjectValidator hasValidator = new SpecialtyHasSubjectValidatorImpl(); 
	private final ExamValidator examValidator = new ExamValidatorImpl();
	
	private ValidatorFactoryImpl(){}
	    
    public static ValidatorFactoryImpl getInstance(){
        return instance;
    }
    
    public UserValidator getUserValidator() {
    	return userValidator;
    }
    
    public UserDetailValidator getUserDetailValidator() {
    	return userDetailValidator;
    }
    
    public MatriculantValidator getMatriculantValidator() {
    	return matriculantValidator;
    }
    
    public FacultyValidator getFacultyValidator() {
    	return facultyValidator;
    }
    
    public TypeStudyValidator getTypeStudyValidator() {
    	return typeStudyValidator;
    }
    
    public SubjectValidator getSubjectValidator() {
    	return subjectValidator;
    }
    
    public PlanValidator getPlanValidator() {
    	return planValidator;
    }
    
    public SpecialtyValidator getSpecialtyValidator() {
    	return specialtyValidator;
    }
    
    public SpecialtyHasSubjectValidator getSpecialtyHasSubjectValidator() {
    	return hasValidator;
    }
    
    public ExamValidator getExamValidator() {
    	return examValidator;
    }
}
