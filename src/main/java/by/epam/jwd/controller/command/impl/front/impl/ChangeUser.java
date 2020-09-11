package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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
import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.UserDetailValidator;
import by.epam.jwd.controller.validator.UserValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ChangeUser implements Command {

	private static Logger logger = LogManager.getLogger(ChangeUser.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserValidator validator = ValidatorFactoryImpl.getInstance().getUserValidator();
		UserDetailValidator userDetailvalidator = ValidatorFactoryImpl.getInstance().getUserDetailValidator();
		
		String page = JSPPageName.USER_DATA;
		
		String login = request.getParameter(FormParameter.USER_LOGIN);
		String password = request.getParameter(FormParameter.USER_PASSWORD);
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
		
		try {		
			UserDetail detail = serviceFactory.getUserDetailService().findUserDetailByIdUser(idUser);
			User user = serviceFactory.getUserService().findUserByIdUser(idUser);
			
			if (user != null) {
				User tmpUser = new User(user.getId(), user.getUserName(), user.getUserPassword(), user.getUserEmail(), user.getUserRole(), user.getUserStatus(), user.getUserIsBanned());
				UserDetail tmpDetail = new UserDetail();
				user = new User(user.getId(), login, password, email, Role.valueOf(role), Status.OFFLINE, Boolean.valueOf(ban));
				
				List<String> validation = validator.validate(user);	
				
				if (detail != null) {
					detail = new UserDetail(1, idUser, firstName, secondName, birthday != "" ? Date.valueOf(birthday) : Date.valueOf(LocalDate.now()) , sex, serialPassport, numberPassport != "" ? Integer.parseInt(numberPassport) : 0);
					tmpDetail = new UserDetail(detail.getId(), detail.getIdUser(), detail.getFirstName(), detail.getSecondName(), detail.getBirthday(), detail.getSex(), detail.getSerialPassport(), detail.getNumberPassport());
					
					validation.addAll(userDetailvalidator.validate(detail));
				}
				
				request.setAttribute(FormParameter.USER_DATA, tmpUser);
				request.setAttribute(FormParameter.USER_DETAIL, tmpDetail);
				
				if (validation.size() == 0) {
					if (serviceFactory.getUserService().update(user)) {
						request.setAttribute(FormParameter.USER_DATA, user);
						
						if (detail != null) {
							serviceFactory.getUserDetailService().update(detail);
							request.setAttribute(FormParameter.USER_DETAIL, detail);
						}
						
						request.setAttribute(MessageParameter.SUCCESS_CHANGE_USER_DATA, MessageParameter.SUCCESS_CHANGE_USER_DATA);
					} else {
						request.setAttribute(MessageParameter.UNSUCCESS_CHANGE_USER_DATA, MessageParameter.UNSUCCESS_CHANGE_USER_DATA);
					}			
				} else {
					validator.getValidation(request, validation);
				}
			} else {
				request.setAttribute(ValidateParameter.EMPTY_USER, ValidateParameter.EMPTY_USER);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in CHANGE_USER method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
