package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Role;
import by.epam.jwd.bean.Status;
import by.epam.jwd.bean.User;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.exception.UserExistServiceException;
import by.epam.jwd.service.factory.ServiceFactory;
import by.epam.jwd.controller.validator.UserValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;

public class Registr implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService serviceImpl = serviceFactory.getUserService();
		UserValidator validator = ValidatorFactoryImpl.getInstance().getUserValidator();
		
		String page = JSPPageName.REGISTR_PAGE;
		
		String userName = request.getParameter(FormParameter.USER_LOGIN);
		String userPassword = request.getParameter(FormParameter.USER_PASSWORD);
		String repeatPassword = request.getParameter(FormParameter.USER_REPEAT_PASSWORD);
		String userEmail = request.getParameter(FormParameter.USER_EMAIL);
		
		try {
			if (!userPassword.equals(repeatPassword)) {
				request.setAttribute(SessionParameter.INVALID_REPEAT_PASSWORD, SessionParameter.INVALID_REPEAT_PASSWORD);
			}
			
			User user = new User(userName, userPassword, userEmail, Role.USER, Status.OFFLINE, false);
			
			List<String> validation = validator.validate(user);
			
			if (validation.size() == 0) {
				serviceImpl.create(user);

				page = JSPPageName.INDEX;
			} else {
				for (String warning : validation) {
					request.setAttribute(warning.toLowerCase(), warning);
				}
			}
		} catch (UserExistServiceException ex) {
			request.setAttribute(SessionParameter.USER_ALREADY_EXIST, SessionParameter.USER_ALREADY_EXIST);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		} 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
	}
}
