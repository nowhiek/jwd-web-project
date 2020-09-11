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
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.PlanValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.PlanService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class UpdatePlan implements Command {

	private static Logger logger = LogManager.getLogger(UpdatePlan.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PlanService serviceImpl = serviceFactory.getPlanService();
		PlanValidator validator = ValidatorFactoryImpl.getInstance().getPlanValidator();
		
		String page = JSPPageName.PLAN_PAGE;
		
		int countPlaces = Integer.parseInt(request.getParameter(FormParameter.COUNT_PLACES).toString());
		
		int idPlan = Integer.parseInt(request.getParameter(FormParameter.ID_PLAN).toString());

		try {
			boolean flag = true;
			
			Plan plan = serviceImpl.findPlanById(idPlan);
			
			if (plan != null) {
				Plan tmp = new Plan(plan.getId(), plan.getCountPlaces());
				request.setAttribute(FormParameter.PLAN, tmp);
				
				plan.setCountPlaces(countPlaces);
				
				List<String> validation = validator.validate(plan);
				
				if (validation.size() == 0) {
					flag = serviceImpl.update(plan);
					
					if (flag) {
						request.setAttribute(MessageParameter.SUCCESS_UPDATE_PLAN, MessageParameter.SUCCESS_UPDATE_PLAN);
						request.setAttribute(FormParameter.PLAN, plan);
					} else {
						request.setAttribute(MessageParameter.UNSUCCESS_UPDATE_PLAN, MessageParameter.UNSUCCESS_UPDATE_PLAN);
					} 
				} else {
					validator.getValidation(request, validation);
				}
			} else {
				request.setAttribute(ValidateParameter.EMPTY_PLAN, ValidateParameter.EMPTY_PLAN);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in UPDATE_PLAN method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
