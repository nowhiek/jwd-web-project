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
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class RemoveSpecialtyHasSubject implements Command {

	private static Logger logger = LogManager.getLogger(RemoveSpecialtyHasSubject.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.SPEC_HAS_SUBJECT_PAGE;
		
		int idSpecialtyHasSubject = Integer.parseInt(request.getParameter(FormParameter.ID_SPECIALTY_HAS_SUBJECT).toString());
		
		try {
			boolean flag = false;
			
			List<Specialty> specialties = serviceFactory.getSpecialtyService().getAllGroupBySpecialtyName();
			List<Subject> subjects = serviceFactory.getSubjectService().getAll();
			
			request.setAttribute(FormParameter.SPECIALTIES, specialties);
			request.setAttribute(FormParameter.SUBJECTS, subjects);
			
			SpecialtyHasSubject has = serviceFactory.getSpecialtyHasSubjectService().findSpecialtyHasSubjectById(idSpecialtyHasSubject);
			
			if (has != null) {
				flag = serviceFactory.getSpecialtyHasSubjectService().remove(has);
				
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_REMOVE_SPECIALTY_HAS_SUBJECT, MessageParameter.SUCCESS_REMOVE_SPECIALTY_HAS_SUBJECT);
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_REMOVE_SPECIALTY_HAS_SUBJECT, MessageParameter.UNSUCCESS_REMOVE_SPECIALTY_HAS_SUBJECT);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in REMOVE_SPECIALTY_HAS_SUBJECT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}