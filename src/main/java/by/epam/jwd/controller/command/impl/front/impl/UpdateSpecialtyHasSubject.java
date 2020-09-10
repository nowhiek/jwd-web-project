package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.SpecialtyHasSubject;
import by.epam.jwd.bean.Subject;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.SpecialtyHasSubjectValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class UpdateSpecialtyHasSubject implements Command {

	private static Logger logger = LogManager.getLogger(UpdateSpecialtyHasSubject.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		SpecialtyHasSubjectValidator validator = ValidatorFactoryImpl.getInstance().getSpecialtyHasSubjectValidator();
		
		String page = JSPPageName.SPEC_HAS_SUBJECT_PAGE;
		
		String specialtyName = request.getParameter(FormParameter.SPECIALTY_NAME);
		String subjectName = request.getParameter(FormParameter.SUBJECT_NAME);
		
		int idSpecialtyHasSubject = Integer.parseInt(request.getParameter(FormParameter.ID_SPECIALTY_HAS_SUBJECT).toString());

		try {
			boolean flag = true;
			
			SpecialtyHasSubject has =  serviceFactory.getSpecialtyHasSubjectService().findSpecialtyHasSubjectById(idSpecialtyHasSubject);
			
			List<Specialty> specialties = serviceFactory.getSpecialtyService().getAllGroupBySpecialtyName();
			List<Subject> subjects = serviceFactory.getSubjectService().getAll();
			
			if (has != null) {
				Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyByName(specialtyName);
				Subject subject = serviceFactory.getSubjectService().findSubjectByName(subjectName);
				
				has.setIdSpecialty(specialty.getId());
				has.setIdSubject(subject.getId());
				
				List<String> validation = validator.validate(has);
				
				request.setAttribute(FormParameter.SPECIALTY, specialty);
				request.setAttribute(FormParameter.SUBJECT, subject);
				request.setAttribute(FormParameter.SUBJECTS, subjects);
				request.setAttribute(FormParameter.SPECIALTIES, specialties);
				request.setAttribute(FormParameter.SPECIALTY_HAS_SUBJECT, has);
				
				if (validation.size() == 0) {
					flag = serviceFactory.getSpecialtyHasSubjectService().update(has);
					
					if (flag) {
						request.setAttribute(MessageParameter.SUCCESS_UPDATE_SPECIALTY_HAS_SUBJECT, MessageParameter.SUCCESS_UPDATE_SPECIALTY_HAS_SUBJECT);
					} else {
						request.setAttribute(MessageParameter.UNSUCCESS_UPDATE_SPECIALTY_HAS_SUBJECT, MessageParameter.UNSUCCESS_UPDATE_SPECIALTY_HAS_SUBJECT);
					} 
				} else {
					validator.getValidation(request, validation);
				}
			} else {
				request.setAttribute(ValidateParameter.EMPTY_SPECIALTY_HAS_SUBJECT, ValidateParameter.EMPTY_SPECIALTY_HAS_SUBJECT);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in UPDATE_SPECIALTY_HAS_SUBJECT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}