package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

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
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.UserValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.exception.UserExistServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class Registr implements Command {

	private static Logger logger = LogManager.getLogger(Registr.class);
	
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
			boolean flag = false;
			
			User userByEmail = serviceImpl.findUserByEmail(userEmail);
			
			if (!userPassword.equals(repeatPassword)) {
				request.setAttribute(ValidateParameter.INVALID_REPEAT_PASSWORD, ValidateParameter.INVALID_REPEAT_PASSWORD);
			} else if (userByEmail != null) {
				request.setAttribute(MessageParameter.EMAIL_ALREADY_EXIST, MessageParameter.EMAIL_ALREADY_EXIST);
			} else {
				User user = new User(userName, userPassword, userEmail, Role.USER, Status.OFFLINE, false);
				
				List<String> validation = validator.validate(user);
				
				if (validation.size() == 0) {
					flag = serviceImpl.create(user);
					
					if (flag) {
						page = JSPPageName.INDEX;	
					}
				} else {
					validator.getValidation(request, validation);
				}
			}
		} catch (UserExistServiceException ex) {
			request.setAttribute(MessageParameter.USER_ALREADY_EXIST, MessageParameter.USER_ALREADY_EXIST);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in REGISTR method.", e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		} 
		
		forwardToPage(request, response, page);
	}
}
