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
import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Plan;
import by.epam.jwd.bean.Result;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.User;
import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class CreateListBySpecialty implements Command {
	
	private static Logger logger = LogManager.getLogger(CreateListBySpecialty.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.LIST_MATRICULANT_PAGE;
		
		int idSpecialty = Integer.parseInt(request.getParameter(FormParameter.ID_SPECIALTY).toString());
		
		List<Result> results = new ArrayList<Result>();
		List<UserDetail> details = new ArrayList<UserDetail>();
		
		try {
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(idSpecialty);
			
			if (specialty != null) {
				Plan plan = serviceFactory.getPlanService().findPlanById(specialty.getIdPlan());
				Faculty faculty = serviceFactory.getFacultyService().findFacultyById(specialty.getIdFaculty());
				
				Result result;
				User user;
				UserDetail detail;
				
				List<Matriculant> matriculants = serviceFactory.getMatriculantService().getListMatriculantsBySpecialty(specialty, plan);
				
				for (Matriculant matriculant : matriculants) {
					result = new Result(1, matriculant.getId(), matriculant.getCertificate(), false);
					user = serviceFactory.getUserService().findUserByIdUser(matriculant.getIdUser());
					detail = serviceFactory.getUserDetailService().findUserDetailByIdUser(user.getId());
					
					serviceFactory.getResultService().create(result); 
					results.add(result);
					details.add(detail);
				}
				
				request.setAttribute(FormParameter.RESULTS, results);
				request.setAttribute(FormParameter.USERS_DETAIL, details);
				request.setAttribute(FormParameter.SPECIALTY, specialty);
				request.setAttribute(FormParameter.PLAN, plan);
				request.setAttribute(FormParameter.FACULTY, faculty);
			} else {
				request.setAttribute(ValidateParameter.EMPTY_SPECIALTY, ValidateParameter.EMPTY_SPECIALTY);
			}
		} catch(ServiceException e) {
			logger.log(Level.ERROR, e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
