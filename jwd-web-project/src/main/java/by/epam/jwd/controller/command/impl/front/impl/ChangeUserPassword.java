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
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;
import by.epam.jwd.controller.validator.util.factory.UserValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.impl.UserValidatorFactoryImpl;

public class ChangeUserPassword implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService serviceImpl = serviceFactory.getUserService();
		UserValidatorFactory validator = UserValidatorFactoryImpl.getInstance();
		
		String page = JSPPageName.USER_PASSWORD_PAGE;
		
		HttpSession session = request.getSession();
		
		String oldPassword = request.getParameter(FormParameter.OLD_PASSWORD);
		String newPassword = request.getParameter(FormParameter.NEW_PASSWORD);
		
		int idUser = Integer.parseInt(session.getAttribute(SessionParameter.ID_USER).toString());
		
		try {
			if (validator.getUserPasswordValidator().validate(oldPassword) && validator.getUserPasswordValidator().validate(newPassword)) {
				User findUserByIdUser = serviceImpl.findUserByIdUser(idUser);
				
				if (findUserByIdUser.getUserPassword().equals(oldPassword)) {
					findUserByIdUser.setUserPassword(newPassword);
					
					boolean isUpdated = serviceImpl.update(findUserByIdUser);
					
					if (isUpdated) {
						request.setAttribute(SessionParameter.SUCCESS_CHANGE_PASSWORD, SessionParameter.SUCCESS_CHANGE_PASSWORD);
					} else {
						request.setAttribute(SessionParameter.UNSUCCESS_CHANGE_PASSWORD, SessionParameter.UNSUCCESS_CHANGE_PASSWORD);
					}
				} else {
					request.setAttribute(SessionParameter.INVALID_NOT_EQUALS_PASSWORD, SessionParameter.INVALID_NOT_EQUALS_PASSWORD);
				}
			} else {
				request.setAttribute(SessionParameter.INVALID_PASSWORD, SessionParameter.INVALID_PASSWORD);
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
