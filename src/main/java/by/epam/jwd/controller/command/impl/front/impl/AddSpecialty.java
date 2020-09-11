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
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.SpecialtyValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class AddSpecialty implements Command {

	private static Logger logger = LogManager.getLogger(AddSpecialty.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		SpecialtyValidator validator = ValidatorFactoryImpl.getInstance().getSpecialtyValidator();
		
		String page = JSPPageName.SPEC_PAGE;
		
		String typeName = request.getParameter(FormParameter.TYPE_NAME);
		String facultyName = request.getParameter(FormParameter.FACULTY_NAME);
		String specialtyName = request.getParameter(FormParameter.SPECIALTY_NAME);
		String qualification = request.getParameter(FormParameter.QUALIFICATION_NAME);
		
		String tmpCountPlaces = request.getParameter(FormParameter.COUNT_PLACES);
		int countPlaces = tmpCountPlaces != null && !tmpCountPlaces.isEmpty() ? Integer.parseInt(tmpCountPlaces) : -1;
		
		String tmpCountYear = request.getParameter(FormParameter.COUNT_YEAR);
		int countYear = tmpCountYear != null && !tmpCountYear.isEmpty() ? Integer.parseInt(tmpCountYear) : -1;
		
		try {
			boolean flag = false;
			
			List<TypeStudy> types = serviceFactory.getTypeStudyService().getAll();
			List<Faculty> faculties = serviceFactory.getFacultyService().getAll();
			List<Plan> plans = serviceFactory.getPlanService().getAll();
			
			TypeStudy type = serviceFactory.getTypeStudyService().findTypeStudyByName(typeName);
			Faculty faculty = serviceFactory.getFacultyService().findFacultyByName(facultyName);
			Plan plan = serviceFactory.getPlanService().findPlanByCountPages(countPlaces);
			
			request.setAttribute(FormParameter.TYPES, types);
			request.setAttribute(FormParameter.FACULTIES, faculties);
			request.setAttribute(FormParameter.PLANS, plans);
			
			if (type != null && faculty != null && plan != null) {				
				Specialty specialty = new Specialty(1, type.getId(), faculty.getId(), plan.getId(), specialtyName, countYear, qualification);
	
				List<String> validation = validator.validate(specialty);

				if (validation.size() == 0) {
					flag = serviceFactory.getSpecialtyService().create(specialty);	
					
					if (flag) {
						request.setAttribute(MessageParameter.SUCCESS_ADD_SPECIALTY, MessageParameter.SUCCESS_ADD_SPECIALTY);
					} else {
						request.setAttribute(MessageParameter.UNSUCCESS_ADD_SPECIALTY, MessageParameter.UNSUCCESS_ADD_SPECIALTY);	
					}
				} else {
					validator.getValidation(request, validation);
				}
			} else if (type == null) {
				request.setAttribute(ValidateParameter.EMPTY_TYPE, ValidateParameter.EMPTY_TYPE);
			} else if (faculty == null) {
				request.setAttribute(ValidateParameter.EMPTY_FACULTY, ValidateParameter.EMPTY_FACULTY);	
			} else if (plan == null) {
				request.setAttribute(ValidateParameter.EMPTY_PLAN, ValidateParameter.EMPTY_PLAN);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in ADD_SPECIALTY method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
