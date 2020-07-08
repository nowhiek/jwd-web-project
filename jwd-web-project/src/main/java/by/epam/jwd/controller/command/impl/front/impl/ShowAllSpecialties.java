package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.ArrayList;
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
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowAllSpecialties implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.SPECIALTIES_PAGE;
		
		List<Specialty> specialties = new ArrayList<Specialty>();
		List<Faculty> faculties = new ArrayList<Faculty>();
		List<TypeStudy> types = new ArrayList<TypeStudy>();
		
		HttpSession session = request.getSession();
		
		try {
			specialties = serviceFactory.getSpecialtyService().getAllGroupBySpecialtyName();
			
			if (specialties != null) {
				for (Specialty specialty : specialties) {
					faculties.add(serviceFactory.getFacultyService().findFacultyById(specialty.getIdFaculty()));
					types.add(serviceFactory.getTypeStudyService().findTypeStudyById(specialty.getIdTypeStudy()));
				}
				
				session.setAttribute(SessionParameter.SPECIALTIES, specialties);
				session.setAttribute(SessionParameter.FACULTIES, faculties);
				session.setAttribute(SessionParameter.TYPE_STUDIES, types);
			} else {
				session.setAttribute(SessionParameter.EMPTY_SPECIALTIES, SessionParameter.EMPTY_SPECIALTIES);
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
