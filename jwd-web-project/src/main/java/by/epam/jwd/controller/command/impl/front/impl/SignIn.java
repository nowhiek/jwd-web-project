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

import by.epam.jwd.bean.User;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.factory.ServiceFactory;

public class SignIn implements Command {
	
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService serviceImpl = serviceFactory.getUserService();
		
		String page = JSPPageName.SIGN_PAGE;
		
		String userName = request.getParameter(FormParameter.USER_LOGIN);
		String userPassword = request.getParameter(FormParameter.USER_PASSWORD);
		
		HttpSession session = request.getSession();
		
		try {
			if (!userName.isEmpty() && !userPassword.isEmpty()) {
				User user = serviceImpl.findUserByLogin(userName);
				
				if (user != null) {
					
					if (user.getUserPassword().equals(userPassword)) {
						if (!user.getUserIsBanned()) {
							page = JSPPageName.AUTH_PAGE;
							
							session.setAttribute(SessionParameter.ID_USER, user.getId());
							session.setAttribute(SessionParameter.NAME_USER, user.getUserName());
							session.setAttribute(SessionParameter.ROLE_USER, user.getUserRole().getTitle());
						} else {
							session.setAttribute(SessionParameter.USER_IS_BLOCKED, SessionParameter.USER_IS_BLOCKED);
						}
					}else {
						session.setAttribute(SessionParameter.INVALID_NOT_EQUALS_PASSWORD, SessionParameter.INVALID_NOT_EQUALS_PASSWORD);
					}
				} else {
					request.setAttribute(SessionParameter.USER_NOT_EXIST, SessionParameter.USER_NOT_EXIST);
				}
			} else if (userName.isEmpty()) {
				request.setAttribute(SessionParameter.EMPTY_LOGIN, SessionParameter.EMPTY_LOGIN);
			} else if (userPassword.isEmpty()) {
				request.setAttribute(SessionParameter.EMPTY_PASSWORD, SessionParameter.EMPTY_PASSWORD);
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
