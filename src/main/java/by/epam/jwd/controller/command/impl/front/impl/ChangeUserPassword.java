package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

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
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.util.factory.UserValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.impl.UserValidatorFactoryImpl;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ChangeUserPassword implements Command {

	private static Logger logger = LogManager.getLogger(ChangeUserPassword.class);
	
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
			if (validator.getUserPasswordValidator().validate(newPassword)) {
				User findUserByIdUser = serviceImpl.findUserByIdUser(idUser);
				
				if (findUserByIdUser.getUserPassword().equals(oldPassword)) {
					findUserByIdUser.setUserPassword(newPassword);
					
					boolean flag = serviceImpl.update(findUserByIdUser);
					
					if (flag) {
						request.setAttribute(MessageParameter.SUCCESS_CHANGE_PASSWORD, MessageParameter.SUCCESS_CHANGE_PASSWORD);
					} else {
						request.setAttribute(MessageParameter.UNSUCCESS_CHANGE_PASSWORD, MessageParameter.UNSUCCESS_CHANGE_PASSWORD);
					}
				} else {
					request.setAttribute(ValidateParameter.INVALID_NOT_EQUALS_PASSWORD, ValidateParameter.INVALID_NOT_EQUALS_PASSWORD);
				}
			} else {
				request.setAttribute(ValidateParameter.INVALID_PASSWORD, ValidateParameter.INVALID_PASSWORD);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in CHANGE_USER_PASSWORD method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
