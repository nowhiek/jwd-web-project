package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.FacultyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class UpdateFaculty implements Command{

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FacultyService serviceImpl = serviceFactory.getFacultyService();
		
		String page = JSPPageName.FACULTY_PAGE;
		
		HttpSession session = request.getSession();
		
		String facultyName = request.getParameter(FormParameter.FACULTY_NAME);
		
		int idFaculty = Integer.parseInt(request.getParameter(FormParameter.ID_FACULTY).toString());

		Faculty faculty = null;
		
		try {
			boolean flag = true;
			
			faculty = serviceImpl.findFacultyById(idFaculty);
			
			if (faculty != null) {
				faculty.setFacultyName(facultyName);
				flag = serviceImpl.update(faculty);
				
				if (flag) {
					session.setAttribute(SessionParameter.SUCCESS_UPDATE_FACULTY, SessionParameter.SUCCESS_UPDATE_FACULTY);
				} else {
					session.setAttribute(SessionParameter.UNSUCCESS_UPDATE_FACULTY, SessionParameter.UNSUCCESS_UPDATE_FACULTY);
				}
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
