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
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class RemoveSpecialty implements Command {

	private static Logger logger = LogManager.getLogger(RemoveSpecialty.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.SPEC_PAGE;
		
		int idSpecialty = Integer.parseInt(request.getParameter(FormParameter.ID_SPECIALTY).toString());
		
		try {
			boolean flag = false;
			
			List<Faculty> faculties = serviceFactory.getFacultyService().getAll();
			List<TypeStudy> types = serviceFactory.getTypeStudyService().getAll();
			List<Plan> plans = serviceFactory.getPlanService().getAll();
			
			request.setAttribute(FormParameter.FACULTIES, faculties);
			request.setAttribute(FormParameter.TYPES, types);
			request.setAttribute(FormParameter.PLANS, plans);
			
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(idSpecialty);
			
			if (specialty != null) {
				flag = serviceFactory.getSpecialtyService().remove(specialty);
				
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_REMOVE_SPECIALTY, MessageParameter.SUCCESS_REMOVE_SPECIALTY);
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_REMOVE_SPECIALTY, MessageParameter.UNSUCCESS_REMOVE_SPECIALTY);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in REMOVE_SPECIALTY method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
