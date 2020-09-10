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
import by.epam.jwd.bean.MatriculantView;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class GetAllMatriculants implements AjaxCommand {

	private static Logger logger = LogManager.getLogger(GetAllMatriculants.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String answer = null;
		Gson gson = new Gson();
		
		List<Matriculant> matriculants = new ArrayList<Matriculant>();
		List<MatriculantView> matriculantsView = new ArrayList<MatriculantView>();
		
		try {	
			matriculants = serviceFactory.getMatriculantService().getAll();		
			
			for (Matriculant matriculant : matriculants) {
				Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(matriculant.getIdSpecialty());
				
				matriculantsView.add(new MatriculantView(matriculant.getId(), matriculant.getIdUser(), specialty.getSpecialtyName(), matriculant.getCertificate(), matriculant.isActivated()));
			}
			
			answer = gson.toJson(matriculantsView); 			
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.setStatus(500);
		}	
		
		return answer;
	}
}
