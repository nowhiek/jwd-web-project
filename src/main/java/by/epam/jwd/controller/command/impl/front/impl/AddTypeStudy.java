package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

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
import by.epam.jwd.controller.validator.TypeStudyValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.TypeStudyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class AddTypeStudy implements Command {

private static Logger logger = LogManager.getLogger(AddTypeStudy.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		TypeStudyService serviceImpl = serviceFactory.getTypeStudyService();
		TypeStudyValidator validator = ValidatorFactoryImpl.getInstance().getTypeStudyValidator();
		
		String page = JSPPageName.TYPE_PAGE;
		
		String typeName = request.getParameter(FormParameter.TYPE_NAME);
		
		TypeStudy type = new TypeStudy(1, typeName);
		
		try {
			boolean flag = false;
			
			List<String> validation = validator.validate(type);
			
			if (validation.size() == 0) {
				flag = serviceImpl.create(type);	
				
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_ADD_TYPE, MessageParameter.SUCCESS_ADD_TYPE);
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_ADD_TYPE, MessageParameter.UNSUCCESS_ADD_TYPE);	
				}
			} else {
				validator.getValidation(request, validation);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in ADD_TYPE_STUDY method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
