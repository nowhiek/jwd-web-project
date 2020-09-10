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
import by.epam.jwd.bean.Plan;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowUpdateSpecialty implements Command {

	private static Logger logger = LogManager.getLogger(ShowUpdateSpecialty.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.SPEC_PAGE;
	
		int idSpecialty = Integer.parseInt(request.getParameter(FormParameter.ID_SPECIALTY).toString());

		try {
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(idSpecialty);
			
			List<TypeStudy> types = serviceFactory.getTypeStudyService().getAll();
			List<Faculty> faculties = serviceFactory.getFacultyService().getAll();
			List<Plan> plans = serviceFactory.getPlanService().getAll();
			
			request.setAttribute(FormParameter.TYPES, types);
			request.setAttribute(FormParameter.FACULTIES, faculties);
			request.setAttribute(FormParameter.PLANS, plans);
			
			if (specialty != null) {
				TypeStudy type = serviceFactory.getTypeStudyService().findTypeStudyById(specialty.getIdTypeStudy());
				Plan plan = serviceFactory.getPlanService().findPlanById(specialty.getIdPlan());
				Faculty faculty = serviceFactory.getFacultyService().findFacultyById(specialty.getIdFaculty());
				
				request.setAttribute(FormParameter.TYPE, type);
				request.setAttribute(FormParameter.FACULTY, faculty);
				request.setAttribute(FormParameter.PLAN, plan);
				request.setAttribute(FormParameter.SPECIALTY, specialty);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SHOW_UPDATE_SPECIALTY method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
