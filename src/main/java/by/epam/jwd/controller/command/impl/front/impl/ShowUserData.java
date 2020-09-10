package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.User;
import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowUserData implements Command {

	private static Logger logger = LogManager.getLogger(ShowUserData.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService serviceImpl = serviceFactory.getUserService();
		
		String page = JSPPageName.USER_DATA;
	
		int idUser = Integer.parseInt(request.getParameter(FormParameter.USER_ID));
		
		try {
			User user = serviceImpl.findUserByIdUser(idUser);
			
			if (user != null) {
				UserDetail detail = serviceFactory.getUserDetailService().findUserDetailByIdUser(user.getId());
				
				request.setAttribute(FormParameter.USER_DATA, user);
				
				if (detail != null) {
					request.setAttribute(FormParameter.USER_DETAIL, detail);
				} else {
					request.setAttribute(ValidateParameter.EMPTY_USER_DETAIL, ValidateParameter.EMPTY_USER_DETAIL);
				}
			} else {
				request.setAttribute(ValidateParameter.EMPTY_USER, ValidateParameter.EMPTY_USER);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SHOW_USER_DATA method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
