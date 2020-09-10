package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.SpecialtyHasSubject;
import by.epam.jwd.bean.Subject;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowSpecialty implements Command {

	private static Logger logger = LogManager.getLogger(ShowSpecialty.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.SPECIALTY_PAGE;
			
		int idSpecialty = Integer.parseInt(request.getParameter(FormParameter.ID_SPECIALTY));
		int idFaculty = Integer.parseInt(request.getParameter(FormParameter.ID_FACULTY));
		
		try {
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(idSpecialty);
			Faculty faculty = serviceFactory.getFacultyService().findFacultyById(idFaculty);
			List<Specialty> specialties = serviceFactory.getSpecialtyService().getSpecialtyByFaculty(specialty, faculty);
			List<TypeStudy> types = serviceFactory.getTypeStudyService().getAllBySpecialty(specialty, faculty);
			List<SpecialtyHasSubject> has = serviceFactory.getSpecialtyHasSubjectService().getAllBySpecialtyId(specialty.getId());
			List<Subject> subjects = new ArrayList<Subject>();
			
			if (specialty != null && faculty != null && types != null && has != null) {
				for (SpecialtyHasSubject h : has) {
					subjects.add(serviceFactory.getSubjectService().findSubjectById(h.getIdSubject()));
				}
				
				request.setAttribute(FormParameter.SPECIALTY, specialty);
				request.setAttribute(FormParameter.SPECIALTIES, specialties);
				request.setAttribute(FormParameter.FACULTY, faculty);
				request.setAttribute(FormParameter.TYPES, types);				
				request.setAttribute(FormParameter.SUBJECTS, subjects);
			} else {
				request.setAttribute(ValidateParameter.EMPTY_SPECIALTY, ValidateParameter.EMPTY_SPECIALTY);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SHOW_SPECIALTY method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
