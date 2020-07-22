package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

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
import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ChangeUser implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.USER_DATA;
		
		String login = request.getParameter(FormParameter.USER_LOGIN);
		String password= request.getParameter(FormParameter.USER_PASSWORD);
		String email = request.getParameter(FormParameter.USER_EMAIL);
		String role = request.getParameter(FormParameter.USER_ROLE);
		String ban = request.getParameter(FormParameter.USER_BAN);
		
		String firstName = request.getParameter(FormParameter.USER_FIRST_NAME);
		String secondName = request.getParameter(FormParameter.USER_SECOND_NAME);
		String birthday = request.getParameter(FormParameter.USER_BIRTHDAY);
		String sex = request.getParameter(FormParameter.USER_SEX);
		String serialPassport = request.getParameter(FormParameter.USER_SERIAL_PASSPORT);
		String numberPassport = request.getParameter(FormParameter.USER_NUMBER_PASSPORT);
		
		int idUser = Integer.parseInt(request.getParameter(FormParameter.USER_ID));
		
		UserDetail detail = null;
		User user = null;
		
		try {		
			detail = serviceFactory.getUserDetailService().findUserDetailByIdUser(idUser);
			user = serviceFactory.getUserService().findUserByIdUser(idUser);
			
			if (user != null) {
				user = new User(user.getId(), login, password, email, Role.valueOf(role), Status.OFFLINE, Boolean.valueOf(ban));
				
				if (detail != null) {
					detail = new UserDetail(1, idUser, firstName, secondName, birthday != "" ? Date.valueOf(birthday) : Date.valueOf(LocalDate.now()) , sex, serialPassport, numberPassport != "" ? Integer.parseInt(numberPassport) : 0);
					serviceFactory.getUserDetailService().update(detail);
				}
				
				if (serviceFactory.getUserService().update(user)) {
					request.setAttribute(SessionParameter.SUCCESS_CHANGE_USER_DATA, SessionParameter.SUCCESS_CHANGE_USER_DATA);
				} else {
					request.setAttribute(SessionParameter.UNSUCCESS_CHANGE_USER_DATA, SessionParameter.UNSUCCESS_CHANGE_USER_DATA);
				}
			} else {
				request.setAttribute(SessionParameter.EMPTY_USER, SessionParameter.EMPTY_USER);
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
