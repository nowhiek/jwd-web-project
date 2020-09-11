package by.epam.jwd.controller.command.impl.ajax.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.service.FacultyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowSpecialtiesByFaculty implements AjaxCommand {

	private static Logger logger = LogManager.getLogger(ShowSpecialtiesByFaculty.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FacultyService serviceImpl = serviceFactory.getFacultyService();
		
		String facultyName = request.getParameter(FormParameter.FACULTY_NAME);
		String typeName = request.getParameter(FormParameter.TYPE_STUDY);
		
		String answer = null;
		Gson gson = new Gson();
		Faculty faculty = null;
		TypeStudy type = null;
		
		try {	
			faculty = serviceImpl.findFacultyByName(facultyName);
			type = serviceFactory.getTypeStudyService().findTypeStudyByName(typeName);
			
			if (faculty != null && type != null) {
				List<Specialty> specialties = serviceFactory.getSpecialtyService().getSpecialtiesByFacultyTypeStudy(faculty, type);
				
				answer = gson.toJson(specialties);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.setStatus(500);
		}	
		
		return answer;
	}
}
