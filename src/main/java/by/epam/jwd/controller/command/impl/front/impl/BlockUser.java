package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

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
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class BlockUser implements Command {

	private static Logger logger = LogManager.getLogger(BlockUser.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService serviceImpl = serviceFactory.getUserService();
		
		String page = JSPPageName.USER_DATA;
		
		int idUser = Integer.parseInt(request.getParameter(FormParameter.USER_ID));
		
		try {
			boolean flag = false;
			User user = serviceImpl.findUserByIdUser(idUser);
			
			if (user != null) {
				user.setUserBan(!user.getUserIsBanned());
				flag = serviceImpl.update(user);
				
				if (flag) {
					page = JSPPageName.ADMIN_PAGE;
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_BAN_USER, MessageParameter.UNSUCCESS_BAN_USER);
				}
			} else {
				request.setAttribute(ValidateParameter.EMPTY_USER, ValidateParameter.EMPTY_USER);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in BLOCK_USER method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
