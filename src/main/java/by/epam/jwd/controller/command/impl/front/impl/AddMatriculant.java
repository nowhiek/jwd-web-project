package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.controller.validator.MatriculantValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class AddMatriculant implements Command {
	
	private static Logger logger = LogManager.getLogger(AddMatriculant.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MatriculantValidator validator = ValidatorFactoryImpl.getInstance().getMatriculantValidator();
		
		String page = JSPPageName.MATRICULANT_PAGE;
		
		String specialtyName = request.getParameter("specialty");
		String certificate = request.getParameter(FormParameter.CERTIFICATE);
		
		Faculty faculty = null;
		TypeStudy type = null;
		
		HttpSession session = request.getSession();
		
		int idUser = Integer.parseInt(session.getAttribute(SessionParameter.ID_USER).toString());
		
		try {
			boolean flag = false;
			
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyByName(specialtyName);
			UserDetail detail = serviceFactory.getUserDetailService().findUserDetailByIdUser(idUser);
			
			if (specialty != null) {
				faculty = serviceFactory.getFacultyService().findFacultyById(specialty.getIdFaculty());
				type = serviceFactory.getTypeStudyService().findTypeStudyById(specialty.getIdTypeStudy());
			}
			
			List<Faculty> faculties = serviceFactory.getFacultyService().getAll();
			List<TypeStudy> types = serviceFactory.getTypeStudyService().getAll();
						
			Matriculant matriculant = new Matriculant(1, idUser, specialty != null ? specialty.getId() : -1, !certificate.isEmpty() ? Integer.parseInt(certificate) : -1, false);
					
			List<String> validation = validator.validate(matriculant);
			
			if (validation.size() == 0) {
				Matriculant findMatriculantByIdMatriculant = serviceFactory.getMatriculantService().findMatriculantByIdUser(idUser);
				
				if (findMatriculantByIdMatriculant != null) {
					flag = serviceFactory.getMatriculantService().update(matriculant);
				} else {
					flag = serviceFactory.getMatriculantService().create(matriculant);
				}
				
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_ADD_MATRICULANT, MessageParameter.SUCCESS_ADD_MATRICULANT);
					request.setAttribute(FormParameter.TYPE, type);
					request.setAttribute(FormParameter.MATRICULANT, matriculant);
					request.setAttribute(FormParameter.FACULTY, faculty);
					request.setAttribute(FormParameter.SPECIALTY, specialty);
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_ADD_MATRICULANT, MessageParameter.UNSUCCESS_ADD_MATRICULANT);
				}
			} else {
				validator.getValidation(request, validation);
				
				request.setAttribute(FormParameter.USER_DETAIL, detail);
				request.setAttribute(FormParameter.FACULTIES, faculties);
				request.setAttribute(FormParameter.TYPES, types);
			}			
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in ADD_MATRICULANT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
