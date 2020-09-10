package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

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
import by.epam.jwd.service.PlanService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class RemovePlan implements Command {

	private static Logger logger = LogManager.getLogger(RemovePlan.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PlanService serviceImpl = serviceFactory.getPlanService();
		
		String page = JSPPageName.PLAN_PAGE;

		int idPlan = Integer.parseInt(request.getParameter(FormParameter.ID_PLAN).toString());

		try {
			boolean flag = false;
			
			Plan plan = serviceImpl.findPlanById(idPlan);
			if (plan != null) {
				flag = serviceImpl.remove(plan);
				
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_REMOVE_PLAN, MessageParameter.SUCCESS_REMOVE_PLAN);
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_REMOVE_PLAN, MessageParameter.UNSUCCESS_REMOVE_PLAN);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in REMOVE_PLAN method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
