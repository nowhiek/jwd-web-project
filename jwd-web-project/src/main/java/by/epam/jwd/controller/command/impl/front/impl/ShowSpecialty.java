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

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowSpecialty implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.SPECIALTY_PAGE;
		
		HttpSession session = request.getSession();
		
		int idSpecialty = Integer.parseInt(request.getParameter(FormParameter.ID_SPECIALTY));
		int idFaculty = Integer.parseInt(request.getParameter(FormParameter.ID_FACULTY));
		
		try {
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(idSpecialty);
			Faculty faculty = serviceFactory.getFacultyService().findFacultyById(idFaculty);
			List<Specialty> specialties = serviceFactory.getSpecialtyService().getSpecialtyByFaculty(specialty, faculty);
			List<TypeStudy> types = serviceFactory.getTypeStudyService().getAllBySpecialty(specialty, faculty);
			
			if (specialty != null && faculty != null && types != null) {
				session.setAttribute(FormParameter.SPECIALTIES, specialties);
				session.setAttribute(FormParameter.FACULTY, faculty);
				session.setAttribute(FormParameter.TYPE_STUDIES, types);
			} else {
				session.setAttribute(SessionParameter.EMPTY_SPECIALTY, SessionParameter.EMPTY_SPECIALTY);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
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
