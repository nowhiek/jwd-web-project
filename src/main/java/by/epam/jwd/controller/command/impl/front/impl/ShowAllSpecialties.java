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
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowAllSpecialties implements Command {

	private static Logger logger = LogManager.getLogger(ShowAllSpecialties.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.SPECIALTIES_PAGE;
		
		List<Faculty> faculties = new ArrayList<Faculty>();
		List<TypeStudy> types = new ArrayList<TypeStudy>();
		
		try {
			List<Specialty> specialties = serviceFactory.getSpecialtyService().getAllGroupBySpecialtyName();
			
			if (specialties.size() != 0) {
				for (Specialty specialty : specialties) {
					faculties.add(serviceFactory.getFacultyService().findFacultyById(specialty.getIdFaculty()));
					types.add(serviceFactory.getTypeStudyService().findTypeStudyById(specialty.getIdTypeStudy()));
				}
						
				request.setAttribute(FormParameter.SPECIALTIES, specialties);
				request.setAttribute(FormParameter.FACULTIES, faculties);
				request.setAttribute(FormParameter.TYPES, types);
			} else {
				request.setAttribute(ValidateParameter.EMPTY_SPECIALTIES, ValidateParameter.EMPTY_SPECIALTIES);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SHOW_ALL_SPECIALTIES method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
