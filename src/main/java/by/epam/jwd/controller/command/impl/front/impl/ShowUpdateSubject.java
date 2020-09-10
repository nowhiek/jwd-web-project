package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Subject;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.service.SubjectService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowUpdateSubject implements Command {
	
	private static Logger logger = LogManager.getLogger(ShowUpdateSubject.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		SubjectService serviceImpl = serviceFactory.getSubjectService();
		
		String page = JSPPageName.SUBJECT_PAGE;
	
		int idSubject = Integer.parseInt(request.getParameter(FormParameter.ID_SUBJECT).toString());

		try {
			Subject subject = serviceImpl.findSubjectById(idSubject);
			
			if (subject!= null) {
				request.setAttribute(FormParameter.SUBJECT, subject);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SHOW_UPDATE_SUBJECT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
