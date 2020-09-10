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

public class UpdateSpecialty implements Command {

	private static Logger logger = LogManager.getLogger(UpdateSpecialty.class);
	
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
		
		int idSpecialty = Integer.parseInt(request.getParameter(FormParameter.ID_SPECIALTY).toString());

		try {
			boolean flag = false;
			
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(idSpecialty);
			
			if (specialty != null) {
				TypeStudy type = serviceFactory.getTypeStudyService().findTypeStudyByName(typeName);
				Faculty faculty = serviceFactory.getFacultyService().findFacultyByName(facultyName);
				Plan plan = serviceFactory.getPlanService().findPlanByCountPages(countPlaces);
				
				List<TypeStudy> types = serviceFactory.getTypeStudyService().getAll();
				List<Faculty> faculties = serviceFactory.getFacultyService().getAll();
				List<Plan> plans = serviceFactory.getPlanService().getAll();
				
				Specialty tmp = new Specialty(specialty.getId(), type.getId(), faculty.getId(), plan.getId(), specialty.getSpecialtyName(), specialty.getCountYearStudy(), specialty.getQualification());
				
				specialty = new Specialty(specialty.getId(), type.getId(), faculty.getId(), plan.getId(), specialtyName, countYear, qualification);
				
				request.setAttribute(FormParameter.TYPES, types);
				request.setAttribute(FormParameter.FACULTIES, faculties);	
				request.setAttribute(FormParameter.PLANS, plans);
				request.setAttribute(FormParameter.PLAN, plan);
				request.setAttribute(FormParameter.SPECIALTY, specialty);
				request.setAttribute(FormParameter.FACULTY, faculty);
				request.setAttribute(FormParameter.TYPE, type);
				request.setAttribute(FormParameter.SPECIALTY, tmp);
				
				List<String> validation = validator.validate(specialty);
				
				if (validation.size() == 0) {
					flag = serviceFactory.getSpecialtyService().update(specialty);	
					
					if (flag) {
						request.setAttribute(MessageParameter.SUCCESS_UPDATE_SPECIALTY, MessageParameter.SUCCESS_UPDATE_SPECIALTY);
					} else {
						request.setAttribute(MessageParameter.UNSUCCESS_UPDATE_SPECIALTY, MessageParameter.UNSUCCESS_UPDATE_SPECIALTY);	
					}
				} else {
					validator.getValidation(request, validation);
				}
			} else {
				request.setAttribute(ValidateParameter.EMPTY_SPECIALTY, ValidateParameter.EMPTY_SPECIALTY);	
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in UPDATE_SPECIALTY method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
