package by.epam.jwd.controller.command.impl.ajax.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import by.epam.jwd.bean.Plan;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.service.PlanService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class GetAllPlans implements AjaxCommand {

	private static Logger logger = LogManager.getLogger(GetAllPlans.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PlanService serviceImpl = serviceFactory.getPlanService();
		
		String answer = null;
		Gson gson = new Gson();
		List<Plan> plans = new ArrayList<Plan>();
		
		try {	
			plans = serviceImpl.getAll();
						
			answer = gson.toJson(plans);			
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.setStatus(500);
		}	
		
		return answer;
	}
}
