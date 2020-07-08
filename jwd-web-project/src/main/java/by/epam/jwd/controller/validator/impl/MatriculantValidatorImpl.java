package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.controller.validator.MatriculantValidator;
import by.epam.jwd.controller.validator.util.factory.MatriculantValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;

public class MatriculantValidatorImpl implements MatriculantValidator {

	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private MatriculantValidatorFactory matriculantValidator = validatorFactory.getMatriculantValidatorFactory();
	
	@Override
	public List<String> validate(Matriculant matriculant) {
		List<String> validation = new ArrayList<String>();
		
		if (!matriculantValidator.getMatriculantCertificateValidator().validate(matriculant.getCertificate())) {
			validation.add(SessionParameter.INVALID_CERTIFICATE);
		}
		
		return validation;
	}
}
