package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;
import by.epam.jwd.controller.validator.MatriculantValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;

public class AddMatriculant implements Command {
	
	private static Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MatriculantValidator validator = ValidatorFactoryImpl.getInstance().getMatriculantValidator();
		
		String page = JSPPageName.MATRICULANT_PAGE;
		
		String specialtyName = request.getParameter(FormParameter.SPECIALTY);
		String certificate = request.getParameter(FormParameter.CERTIFICATE);
				
		Matriculant matriculant = null;
		
		HttpSession session = request.getSession();
		
		int idUser = Integer.parseInt(session.getAttribute(SessionParameter.ID_USER).toString());
		
		try {
			boolean flag = false;
			
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyByName(specialtyName);
		
			matriculant = new Matriculant(1, idUser, specialty.getId(), Integer.parseInt(certificate), false);
			
			List<String> validation = validator.validate(matriculant);
			
			if (validation.size() == 0) {
				Matriculant findMatriculantByIdMatriculant = serviceFactory.getMatriculantService().findMatriculantByIdUser(idUser);
				
				if (findMatriculantByIdMatriculant != null) {
					flag = serviceFactory.getMatriculantService().update(matriculant);
				} else {
					flag = serviceFactory.getMatriculantService().create(matriculant);
				}
				
				if (flag) {
					request.setAttribute(SessionParameter.SUCCESS_ADD_MATRICULANT, SessionParameter.SUCCESS_ADD_MATRICULANT);
				} else {
					request.setAttribute(SessionParameter.UNSUCCESS_ADD_MATRICULANT, SessionParameter.UNSUCCESS_ADD_MATRICULANT);
				}
			} else {
				for (String warning : validation) {
					request.setAttribute(warning.toLowerCase(), warning);
				}
			}			
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e.getStackTrace());
			page = JSPPageName.ERROR_PAGE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
	}
}
