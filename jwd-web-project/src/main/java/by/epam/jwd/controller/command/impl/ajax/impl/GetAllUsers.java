package by.epam.jwd.controller.command.impl.ajax.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import by.epam.jwd.bean.User;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class GetAllUsers implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService serviceImpl = serviceFactory.getUserService();
		
		String answer = null;
		Gson gson = new Gson();
		List<User> users = new ArrayList<User>();
		
		try {	
			users = serviceImpl.getAll();
			
			answer = gson.toJson(users);
		} catch (ServiceException e) {
			response.setStatus(500);
		}	
		
		return answer;
	}
}
