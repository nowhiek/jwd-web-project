package by.epam.jwd.controller.command.impl.ajax.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.service.MatriculantService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class GetAllMatriculantsBySpecialty implements AjaxCommand {

	private static Logger logger = LogManager.getLogger(GetAllMatriculantsBySpecialty.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MatriculantService serviceImpl = serviceFactory.getMatriculantService();
		
		String answer = null;
		Gson gson = new Gson();
		
		int idSpecialty = Integer.parseInt(request.getParameter(FormParameter.ID_SPECIALTY).toString());
		
		List<Matriculant> matriculants = new ArrayList<Matriculant>();
		Specialty specialty = null;
		
		try {	
			specialty = serviceFactory.getSpecialtyService().findSpecialtyById(idSpecialty);
			
			matriculants = serviceImpl.getAllMatriculantsBySpecialty(specialty);
			
			answer = gson.toJson(matriculants);			
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.setStatus(500);
		}	
		
		return answer;
	}
}
