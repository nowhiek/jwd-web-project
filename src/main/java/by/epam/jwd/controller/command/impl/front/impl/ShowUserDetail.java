package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.UserDetailService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowUserDetail implements Command {

	private static Logger logger = LogManager.getLogger(ShowUserDetail.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserDetailService serviceImpl = serviceFactory.getUserDetailService();
		
		String page = JSPPageName.USER_PAGE;
		
		HttpSession session = request.getSession();
		
		int idUser = Integer.parseInt(session.getAttribute(SessionParameter.ID_USER).toString());
		
		try {
			UserDetail detail = serviceImpl.findUserDetailByIdUser(idUser);
			
			if (detail != null) {
				request.setAttribute(FormParameter.USER_DETAIL, detail);
			} else { 
				request.setAttribute(ValidateParameter.EMPTY_USER_DETAIL, ValidateParameter.EMPTY_USER_DETAIL);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SHOW_USER_DETAIL method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
