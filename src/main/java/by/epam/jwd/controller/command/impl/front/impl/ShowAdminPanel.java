package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.User;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowAdminPanel implements Command {
	
	private static Logger logger = LogManager.getLogger(ShowAdminPanel.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService serviceImpl = serviceFactory.getUserService();
		
		String page = JSPPageName.ADMIN_PAGE;
		
		try {
			List<User> users = serviceImpl.getAll();

			if (users != null && !users.isEmpty()) {
				request.setAttribute(FormParameter.USERS, users);
			} else {
				request.setAttribute(ValidateParameter.EMPTY_USERS, ValidateParameter.EMPTY_USERS);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SHOW_ADMIN_PANEL method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
