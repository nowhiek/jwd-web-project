package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
import by.epam.jwd.controller.validator.UserDetailValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;

public class ChangeUserDetail implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserDetailService serviceImpl = serviceFactory.getUserDetailService();
		UserDetailValidator validator = ValidatorFactoryImpl.getInstance().getUserDetailValidator();
		
		String page = JSPPageName.USER_PAGE;
		
		String firstName = request.getParameter(FormParameter.USER_FIRST_NAME);
		String secondName = request.getParameter(FormParameter.USER_SECOND_NAME);
		String birthday = request.getParameter(FormParameter.USER_BIRTHDAY);
		String sex = request.getParameter(FormParameter.USER_SEX);
		String serialPassport = request.getParameter(FormParameter.USER_SERIAL_PASSPORT);
		String numberPassport = request.getParameter(FormParameter.USER_NUMBER_PASSPORT);
		
		HttpSession session = request.getSession();
		
		int idUser = Integer.parseInt(session.getAttribute(SessionParameter.ID_USER).toString());
		
		try {
			boolean flag = false;
			
			UserDetail findUserDetailByIdUser = serviceImpl.findUserDetailByIdUser(idUser);
			UserDetail detail = new UserDetail(1, idUser, firstName, secondName, birthday != "" ? Date.valueOf(birthday) : Date.valueOf(LocalDate.now()) , sex, serialPassport, numberPassport != "" ? Integer.parseInt(numberPassport) : 0);
			List<String> validation = validator.validate(detail);
		
			if (validation.size() == 0) {	
				if (findUserDetailByIdUser != null) {
					flag = serviceImpl.update(detail);
				} else {
					flag = serviceImpl.create(detail);
				}
				
				if (flag) {
					request.setAttribute(SessionParameter.SUCCESS_UPDATE_USER_DETAIL, SessionParameter.SUCCESS_UPDATE_USER_DETAIL);
				} else {
					request.setAttribute(SessionParameter.UNSUCCESS_UPDATE_USER_DETAIL, SessionParameter.UNSUCCESS_UPDATE_USER_DETAIL);
				}
			} else {
				for (String warning : validation) {
					request.setAttribute(warning.toLowerCase(), warning);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e.getStackTrace());
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
