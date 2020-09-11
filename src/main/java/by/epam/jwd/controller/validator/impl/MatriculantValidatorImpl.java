package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.MatriculantValidator;
import by.epam.jwd.controller.validator.util.factory.MatriculantValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class MatriculantValidatorImpl implements MatriculantValidator {

	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private MatriculantValidatorFactory matriculantValidator = validatorFactory.getMatriculantValidatorFactory();
	
	@Override
	public List<String> validate(Matriculant matriculant) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		List<String> validation = new ArrayList<String>();
		
		try {
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(matriculant.getIdSpecialty());
			
			if (!matriculantValidator.getMatriculantCertificateValidator().validate(matriculant.getCertificate())) {
				validation.add(ValidateParameter.INVALID_CERTIFICATE);
			}
			
			if (!matriculantValidator.getMatriculantSpecialtyNameValidator().validate(specialty != null ? specialty.getSpecialtyName() : null)) {
				validation.add(ValidateParameter.INVALID_SPECIALTY_NAME);
			}
		} catch(ServiceException e) {
			e.printStackTrace();
		}
		
		return validation;
	}
}
