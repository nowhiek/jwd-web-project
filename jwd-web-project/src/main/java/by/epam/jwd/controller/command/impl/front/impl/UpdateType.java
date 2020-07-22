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

import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.TypeStudyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class UpdateType implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		TypeStudyService serviceImpl = serviceFactory.getTypeStudyService();
		
		String page = JSPPageName.TYPE_PAGE;
		
		HttpSession session = request.getSession();
		
		String typeName = request.getParameter(FormParameter.TYPE_NAME);
		
		int idFaculty = Integer.parseInt(request.getParameter(FormParameter.ID_TYPE).toString());

		TypeStudy type = null;
		
		try {
			boolean flag = true;
			
			type = serviceImpl.findTypeStudyById(idFaculty);
			
			if (type != null) {
				type.setStudyName(typeName);
				flag = serviceImpl.update(type);
				
				if (flag) {
					session.setAttribute(SessionParameter.SUCCESS_UPDATE_TYPE, SessionParameter.SUCCESS_UPDATE_TYPE);
				} else {
					session.setAttribute(SessionParameter.UNSUCCESS_UPDATE_TYPE, SessionParameter.UNSUCCESS_UPDATE_TYPE);
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