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
import by.epam.jwd.service.MatriculantService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowMatriculant implements Command {

	private static Logger logger = LogManager.getLogger(ShowMatriculant.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MatriculantService serviceImpl = serviceFactory.getMatriculantService();
		
		String page = JSPPageName.MATRICULANT_PAGE;
		
		HttpSession session = request.getSession();
		
		try {
			int idUser = Integer.parseInt(session.getAttribute(SessionParameter.ID_USER).toString());
			
			Matriculant matriculant = serviceImpl.findMatriculantByIdUser(idUser);
				
			if (matriculant != null) {
				Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(matriculant.getIdSpecialty());
				Faculty faculty = serviceFactory.getFacultyService().findFacultyById(specialty.getIdFaculty());
				TypeStudy type = serviceFactory.getTypeStudyService().findTypeStudyById(specialty.getIdTypeStudy());
				
				request.setAttribute(FormParameter.TYPE, type);
				request.setAttribute(FormParameter.FACULTY, faculty);
				request.setAttribute(FormParameter.SPECIALTY, specialty);
				request.setAttribute(FormParameter.MATRICULANT, matriculant);
				
				if (matriculant.isActivated()) {
					request.setAttribute(MessageParameter.MATRICULANT_ACCEPTED, MessageParameter.MATRICULANT_ACCEPTED);
				}
			} else { 			
				List<Faculty> faculties = serviceFactory.getFacultyService().getAll();
				List<TypeStudy> types = serviceFactory.getTypeStudyService().getAll();
				UserDetail detail = serviceFactory.getUserDetailService().findUserDetailByIdUser(idUser);
								
				request.setAttribute(FormParameter.USER_DETAIL, detail);
				request.setAttribute(FormParameter.FACULTIES, faculties);
				request.setAttribute(FormParameter.TYPES, types);
				
				if (detail == null) {
					request.setAttribute(MessageParameter.FILL_OUT_FORM_ABOUT_YOURSELF, MessageParameter.FILL_OUT_FORM_ABOUT_YOURSELF);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SHOW_MATRICULANT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
