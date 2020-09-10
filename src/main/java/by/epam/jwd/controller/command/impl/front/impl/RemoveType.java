package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.service.TypeStudyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class RemoveType implements Command {

	private static Logger logger = LogManager.getLogger(RemoveType.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		TypeStudyService serviceImpl = serviceFactory.getTypeStudyService();
		
		String page = JSPPageName.TYPE_PAGE;
		
		int idType = Integer.parseInt(request.getParameter(FormParameter.ID_TYPE).toString());
		
		try {
			boolean flag = false;
			
			TypeStudy type = serviceImpl.findTypeStudyById(idType);

			if (type != null) {
				flag = serviceImpl.remove(type);
				
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_REMOVE_TYPE, MessageParameter.SUCCESS_REMOVE_TYPE);	
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_REMOVE_TYPE, MessageParameter.UNSUCCESS_REMOVE_TYPE);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in REMOVE_TYPE method.", e);
			page = JSPPageName.ERROR_PAGE;
		}	
		
		forwardToPage(request, response, page);
	}
}
