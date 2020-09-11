package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.User;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.MatriculantValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class UpdateMatriculant implements Command {

	private static Logger logger = LogManager.getLogger(UpdateMatriculant.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MatriculantValidator validator = ValidatorFactoryImpl.getInstance().getMatriculantValidator();
		
		String page = JSPPageName.MATR_PAGE;
		
		String specialtyName = request.getParameter(FormParameter.SPECIALTY_NAME);
		
		String tmpCertificate = request.getParameter(FormParameter.CERTIFICATE);
		int certificate = tmpCertificate != null && !tmpCertificate.isEmpty() ? Integer.parseInt(tmpCertificate) : -1;
		
		int idMatriculant = Integer.parseInt(request.getParameter(FormParameter.ID_MATRICULANT).toString());

		try {
			boolean flag = true;
			
			Matriculant matriculant = serviceFactory.getMatriculantService().findMatriculantById(idMatriculant);
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyByName(specialtyName);
			List<Specialty> specialties = serviceFactory.getSpecialtyService().getAllGroupBySpecialtyName();
			
			if (matriculant != null) {
				Matriculant tmp = new Matriculant(matriculant.getId(), matriculant.getIdUser(), matriculant.getIdSpecialty(), matriculant.getCertificate(), matriculant.isActivated());
				
				matriculant.setCertificate(certificate);
				matriculant.setIdSpecialty(specialty.getId());
				
				User user = serviceFactory.getUserService().findUserByIdUser(matriculant.getIdUser());
				
				request.setAttribute(FormParameter.SPECIALTIES, specialties);
				request.setAttribute(FormParameter.SPECIALTY, specialty);	
				request.setAttribute(FormParameter.USER_DATA, user);
				request.setAttribute(FormParameter.MATRICULANT, tmp);
				
				List<String> validation = validator.validate(matriculant);
				
				if (validation.size() == 0) {
					flag = serviceFactory.getMatriculantService().update(matriculant);
					
					if (flag) {
						request.setAttribute(MessageParameter.SUCCESS_UPDATE_MATRICULANT, MessageParameter.SUCCESS_UPDATE_MATRICULANT);
					} else {
						request.setAttribute(MessageParameter.UNSUCCESS_UPDATE_MATRICULANT, MessageParameter.UNSUCCESS_UPDATE_MATRICULANT);
					}
				} else {
					validator.getValidation(request, validation);
				}
			} else {
				request.setAttribute(ValidateParameter.EMPTY_MATRICULANT, ValidateParameter.EMPTY_MATRICULANT);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in UPDATE_MATRICULANT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
