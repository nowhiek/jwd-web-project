package by.epam.jwd.controller.command.impl.ajax.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Plan;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.SpecialtyView;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class GetAllSpecialties implements AjaxCommand {

	private static Logger logger = LogManager.getLogger(GetAllSpecialties.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String answer = null;
		Gson gson = new Gson();

		List<Specialty> specialties = new ArrayList<Specialty>();
		List<SpecialtyView> specialtiesView = new ArrayList<SpecialtyView>();
		
		try {
			specialties = serviceFactory.getSpecialtyService().getAll();
					
			for (Specialty specialty : specialties) {
				TypeStudy type = serviceFactory.getTypeStudyService().findTypeStudyById(specialty.getIdTypeStudy());
				Faculty faculty = serviceFactory.getFacultyService().findFacultyById(specialty.getIdFaculty());
				Plan plan = serviceFactory.getPlanService().findPlanById(specialty.getIdPlan());
			
				specialtiesView.add(new SpecialtyView(specialty.getId(), type.getStudyName(), faculty.getFacultyName(), plan.getCountPlaces(), specialty.getSpecialtyName(), specialty.getCountYearStudy()));
			}

			answer = gson.toJson(specialtiesView);						
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.setStatus(500);
		}	
		
		return answer;
	}
}
