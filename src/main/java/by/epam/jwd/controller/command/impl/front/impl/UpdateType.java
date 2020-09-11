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
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.TypeStudyValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.TypeStudyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class UpdateType implements Command {

	private static Logger logger = LogManager.getLogger(UpdateType.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		TypeStudyService serviceImpl = serviceFactory.getTypeStudyService();
		TypeStudyValidator validator = ValidatorFactoryImpl.getInstance().getTypeStudyValidator();
		
		String page = JSPPageName.TYPE_PAGE;
		
		String typeName = request.getParameter(FormParameter.TYPE_NAME);
		
		int idFaculty = Integer.parseInt(request.getParameter(FormParameter.ID_TYPE).toString());

		try {
			boolean flag = true;
			
			TypeStudy type = serviceImpl.findTypeStudyById(idFaculty);
			
			if (type != null) {
				TypeStudy tmp = new TypeStudy(type.getId(), type.getStudyName());
				request.setAttribute(FormParameter.TYPE, tmp);
				
				type.setStudyName(typeName);

				List<String> validation = validator.validate(type);

				if (validation.size() == 0) {
					flag = serviceImpl.update(type);
					
					if (flag) {
						request.setAttribute(MessageParameter.SUCCESS_UPDATE_TYPE, MessageParameter.SUCCESS_UPDATE_TYPE);
						request.setAttribute(FormParameter.TYPE, type);
					} else {
						request.setAttribute(MessageParameter.UNSUCCESS_UPDATE_TYPE, MessageParameter.UNSUCCESS_UPDATE_TYPE);
					}
				} else {
					validator.getValidation(request, validation);
				}
			} else {
				request.setAttribute(ValidateParameter.EMPTY_TYPE, ValidateParameter.EMPTY_TYPE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in UPDATE_TYPE method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
