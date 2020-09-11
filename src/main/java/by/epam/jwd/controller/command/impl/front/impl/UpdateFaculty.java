package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.FacultyValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.FacultyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class UpdateFaculty implements Command{

	private static Logger logger = LogManager.getLogger(UpdateFaculty.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FacultyService serviceImpl = serviceFactory.getFacultyService();
		FacultyValidator validator = ValidatorFactoryImpl.getInstance().getFacultyValidator();
		
		String page = JSPPageName.FACULTY_PAGE;
		
		String facultyName = request.getParameter(FormParameter.FACULTY_NAME);
		
		int idFaculty = Integer.parseInt(request.getParameter(FormParameter.ID_FACULTY).toString());
		
		try {
			boolean flag = true;
			
			Faculty faculty = serviceImpl.findFacultyById(idFaculty);
			
			if (faculty != null) {
				Faculty tmp = new Faculty(faculty.getId(), faculty.getFacultyName());
				request.setAttribute(FormParameter.FACULTY, tmp);
				
				faculty.setFacultyName(facultyName);
				
				List<String> validation = validator.validate(faculty);
				
				if (validation.size() == 0) { 
					flag = serviceImpl.update(faculty);
					
					if (flag) {
						request.setAttribute(MessageParameter.SUCCESS_UPDATE_FACULTY, MessageParameter.SUCCESS_UPDATE_FACULTY);
						request.setAttribute(FormParameter.FACULTY, faculty);
					} else {
						request.setAttribute(MessageParameter.UNSUCCESS_UPDATE_FACULTY, MessageParameter.UNSUCCESS_UPDATE_FACULTY);
					}
				} else {
					validator.getValidation(request, validation);
				}
			} else {
				request.setAttribute(ValidateParameter.EMPTY_FACULTY, ValidateParameter.EMPTY_FACULTY);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in UPDATE_FACULTY method.", e);
			page = JSPPageName.ERROR_PAGE;
		}

		forwardToPage(request, response, page);
	}
}
