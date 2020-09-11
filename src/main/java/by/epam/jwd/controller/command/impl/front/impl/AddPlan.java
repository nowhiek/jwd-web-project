package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Plan;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.validator.PlanValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.PlanService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class AddPlan implements Command {

	private static Logger logger = LogManager.getLogger(AddPlan.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PlanService serviceImpl = serviceFactory.getPlanService();
		PlanValidator validator = ValidatorFactoryImpl.getInstance().getPlanValidator();

		String page = JSPPageName.PLAN_PAGE;
		
		String tmpPlanCountPlaces = request.getParameter(FormParameter.COUNT_PLACES);
		int planCountPlaces = tmpPlanCountPlaces != null && !tmpPlanCountPlaces.isEmpty() ? Integer.parseInt(tmpPlanCountPlaces) : -1;
		
		Plan plan = new Plan(1, planCountPlaces);
		
		try {
			boolean flag = false;
			
			List<String> validation = validator.validate(plan);
			
			if (validation.size() == 0) {
				flag = serviceImpl.create(plan);	
				
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_ADD_PLAN, MessageParameter.SUCCESS_ADD_PLAN);
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_ADD_PLAN, MessageParameter.UNSUCCESS_ADD_PLAN);	
				}
			} else {
				validator.getValidation(request, validation);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in ADD_PLAN method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
