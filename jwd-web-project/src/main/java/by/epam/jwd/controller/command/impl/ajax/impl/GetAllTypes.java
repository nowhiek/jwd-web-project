package by.epam.jwd.controller.command.impl.ajax.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.service.TypeStudyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class GetAllTypes implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		TypeStudyService serviceImpl = serviceFactory.getTypeStudyService();
		
		String answer = null;
		Gson gson = new Gson();
		List<TypeStudy> types = new ArrayList<TypeStudy>();
		
		try {	
			types = serviceImpl.getAll();
			
			answer = gson.toJson(types);
		} catch (ServiceException e) {
			response.setStatus(500);
		}	
		
		return answer;
	}
}
