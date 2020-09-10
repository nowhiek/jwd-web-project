package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Subject;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.validator.SubjectValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.SubjectService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class AddSubject implements Command {
	
	private static Logger logger = LogManager.getLogger(AddSpecialty.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		SubjectService serviceImpl = serviceFactory.getSubjectService();
		SubjectValidator validator = ValidatorFactoryImpl.getInstance().getSubjectValidator();
		
		String page = JSPPageName.SUBJECT_PAGE;
		
		String subjectName = request.getParameter(FormParameter.SUBJECT_NAME);
		
		Subject subject = new Subject(1, subjectName);
		
		try {
			boolean flag = false;
			
			List<String> validation = validator.validate(subject);
			
			if (validation.size() == 0) {
				flag = serviceImpl.create(subject);	
				
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_ADD_SUBJECT, MessageParameter.SUCCESS_ADD_SUBJECT);
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_ADD_SUBJECT, MessageParameter.UNSUCCESS_ADD_SUBJECT);	
				}
			} else {
				validator.getValidation(request, validation);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in ADD_SUBJECT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
