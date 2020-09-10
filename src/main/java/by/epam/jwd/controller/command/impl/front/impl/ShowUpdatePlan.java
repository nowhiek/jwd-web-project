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
import by.epam.jwd.service.PlanService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowUpdatePlan implements Command {

	private static Logger logger = LogManager.getLogger(ShowUpdatePlan.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PlanService serviceImpl = serviceFactory.getPlanService();
		
		String page = JSPPageName.PLAN_PAGE;
		
		int idPlan = Integer.parseInt(request.getParameter(FormParameter.ID_PLAN).toString());

		try {
			Plan plan = serviceImpl.findPlanById(idPlan);
			
			if (plan!= null) {
				request.setAttribute(FormParameter.PLAN, plan);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SHOW_UPDATE_PLAN method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
