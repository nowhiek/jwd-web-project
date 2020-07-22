package by.epam.jwd.controller.command.impl.ajax.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.service.FacultyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class GetAllFaculties implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FacultyService serviceImpl = serviceFactory.getFacultyService();
		
		String answer = null;
		Gson gson = new Gson();
		List<Faculty> faculties = new ArrayList<Faculty>();
		
		try {	
			faculties = serviceImpl.getAll();
			
			answer = gson.toJson(faculties);
		} catch (ServiceException e) {
			response.setStatus(500);
		}	
		
		return answer;
	}
}
