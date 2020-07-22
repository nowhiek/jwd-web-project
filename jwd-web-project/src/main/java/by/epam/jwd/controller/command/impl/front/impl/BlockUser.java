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

public class BlockUser implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService serviceImpl = serviceFactory.getUserService();
		
		String page = JSPPageName.USER_DATA;
		HttpSession session = request.getSession();
		
		int idUser = Integer.parseInt(request.getParameter(FormParameter.USER_ID));
		
		User user = null;
		
		try {
			boolean flag = false;
			user = serviceImpl.findUserByIdUser(idUser);
			
			if (user != null) {
				user.setUserBan(!user.getUserIsBanned());
				flag = serviceImpl.update(user);
				
				if (flag) {
					page = JSPPageName.ADMIN_PAGE;
				} else {
					session.setAttribute(SessionParameter.UNSUCCESS_BAN_USER, SessionParameter.UNSUCCESS_BAN_USER);
				}
			} else {
				
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
