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

import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.UserDetailService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowUserDetail implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserDetailService serviceImpl = serviceFactory.getUserDetailService();
		
		String page = JSPPageName.USER_PAGE;
		
		HttpSession session = request.getSession();
		
		try {
			int idUser = Integer.parseInt(session.getAttribute(SessionParameter.ID_USER).toString());
			
			UserDetail findUserDetailByIdUser = serviceImpl.findUserDetailByIdUser(idUser);
				
			if (findUserDetailByIdUser != null) {
				session.setAttribute(FormParameter.USER_FIRST_NAME, findUserDetailByIdUser.getFirstName());
				session.setAttribute(FormParameter.USER_SECOND_NAME, findUserDetailByIdUser.getSecondName());
				session.setAttribute(FormParameter.USER_BIRTHDAY, findUserDetailByIdUser.getBirthday());
				session.setAttribute(FormParameter.USER_SEX, findUserDetailByIdUser.getSex());
				session.setAttribute(FormParameter.USER_SERIAL_PASSPORT, findUserDetailByIdUser.getSerialPassport());
				session.setAttribute(FormParameter.USER_NUMBER_PASSPORT, findUserDetailByIdUser.getNumberPassport());
			} else { 
				session.removeAttribute(FormParameter.USER_FIRST_NAME);
		        session.removeAttribute(FormParameter.USER_SECOND_NAME);
		        session.removeAttribute(FormParameter.USER_BIRTHDAY);
		        session.removeAttribute(FormParameter.USER_SEX);
		        session.removeAttribute(FormParameter.USER_SERIAL_PASSPORT);
				session.removeAttribute(FormParameter.USER_NUMBER_PASSPORT);
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
