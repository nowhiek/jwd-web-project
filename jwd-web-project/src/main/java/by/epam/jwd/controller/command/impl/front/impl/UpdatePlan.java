package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Plan;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.PlanService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class UpdatePlan implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PlanService serviceImpl = serviceFactory.getPlanService();
		
		String page = JSPPageName.PLAN_PAGE;
		
		HttpSession session = request.getSession();
		
		int countPlaces = Integer.parseInt(request.getParameter(FormParameter.COUNT_PLACES).toString());
		
		int idPlan = Integer.parseInt(request.getParameter(FormParameter.ID_PLAN).toString());

		Plan plan = null;
		
		try {
			boolean flag = true;
			
			plan = serviceImpl.findPlanById(idPlan);
			
			if (plan != null) {
				plan.setCountPlaces(countPlaces);
				flag = serviceImpl.update(plan);
				
				if (flag) {
					session.setAttribute(SessionParameter.SUCCESS_UPDATE_PLAN, SessionParameter.SUCCESS_UPDATE_PLAN);
				} else {
					session.setAttribute(SessionParameter.UNSUCCESS_UPDATE_PLAN, SessionParameter.UNSUCCESS_UPDATE_PLAN);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
	}
}
