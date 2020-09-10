package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.User;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class SignIn implements Command {
	
	private static Logger logger = LogManager.getLogger(SignIn.class);
	
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
					String hashedPassword = DigestUtils.md5Hex(userPassword);
					if (user.getUserPassword().equals(hashedPassword)) {
						if (!user.getUserIsBanned()) {
							page = JSPPageName.AUTH_PAGE;
							
							session.setAttribute(SessionParameter.ID_USER, user.getId());
							session.setAttribute(SessionParameter.NAME_USER, user.getUserName());
							session.setAttribute(SessionParameter.ROLE_USER, user.getUserRole().getTitle());
						} else {
							request.setAttribute(MessageParameter.USER_IS_BLOCKED, MessageParameter.USER_IS_BLOCKED);
						}
					}else {
						request.setAttribute(ValidateParameter.INVALID_NOT_EQUALS_PASSWORD, ValidateParameter.INVALID_NOT_EQUALS_PASSWORD);
					}
				} else {
					request.setAttribute(MessageParameter.USER_NOT_EXIST, MessageParameter.USER_NOT_EXIST);
				}
			} else if (userName.isEmpty() && userPassword.isEmpty()) {
				request.setAttribute(ValidateParameter.EMPTY_LOGIN, ValidateParameter.EMPTY_LOGIN);
				request.setAttribute(ValidateParameter.EMPTY_PASSWORD, ValidateParameter.EMPTY_PASSWORD);
			}
			else if (userName.isEmpty()) {
				request.setAttribute(ValidateParameter.EMPTY_LOGIN, ValidateParameter.EMPTY_LOGIN);
			} else if (userPassword.isEmpty()) {
				request.setAttribute(ValidateParameter.EMPTY_PASSWORD, ValidateParameter.EMPTY_PASSWORD);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SIGN_IN method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
