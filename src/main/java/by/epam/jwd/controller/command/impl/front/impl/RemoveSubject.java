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
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.service.SubjectService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class RemoveSubject implements Command {

	private static Logger logger = LogManager.getLogger(RemoveSubject.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		SubjectService serviceImpl = serviceFactory.getSubjectService();
		
		String page = JSPPageName.SUBJECT_PAGE;
	
		int idSubject = Integer.parseInt(request.getParameter(FormParameter.ID_SUBJECT).toString());

		try {
			boolean flag = false;
			
			Subject subject = serviceImpl.findSubjectById(idSubject);
			
			if (subject != null) {
				flag = serviceImpl.remove(subject);
				
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_REMOVE_SUBJECT, MessageParameter.SUCCESS_REMOVE_SUBJECT);
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_REMOVE_SUBJECT, MessageParameter.UNSUCCESS_REMOVE_SUBJECT);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in REMOVE_SUBJECT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
