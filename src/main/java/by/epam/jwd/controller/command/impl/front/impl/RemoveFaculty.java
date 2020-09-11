package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.service.FacultyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class RemoveFaculty implements Command {

	private static Logger logger = LogManager.getLogger(RemoveFaculty.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FacultyService serviceImpl = serviceFactory.getFacultyService();
		
		String page = JSPPageName.FACULTY_PAGE;
		
		int idFaculty = Integer.parseInt(request.getParameter(FormParameter.ID_FACULTY).toString());
		
		try {
			boolean flag = false;
			
			Faculty faculty = serviceImpl.findFacultyById(idFaculty);
			
			if (faculty != null) {
				flag = serviceImpl.remove(faculty);
				
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_REMOVE_FACULTY, MessageParameter.SUCCESS_REMOVE_FACULTY);
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_REMOVE_FACULTY, MessageParameter.UNSUCCESS_REMOVE_FACULTY);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in REMOVE_FACULTY method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
