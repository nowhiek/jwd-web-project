package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Exam;
import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.SpecialtyHasSubject;
import by.epam.jwd.bean.Subject;
import by.epam.jwd.bean.User;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class DeactivateMatriculant implements Command {

	private static Logger logger = LogManager.getLogger(DeactivateMatriculant.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.MATR_PAGE;
		
		int idUser = Integer.parseInt(request.getParameter(FormParameter.USER_ID).toString());
		int idMatriculant = Integer.parseInt(request.getParameter(FormParameter.ID_MATRICULANT).toString());
		int countSubjects = Integer.parseInt(request.getParameter(FormParameter.COUNT_SUBJECTS).toString());
		
		List<Subject> subjectsDB = new ArrayList<Subject>();
		
		try {
			boolean flag = false;
			
			Matriculant matriculant = serviceFactory.getMatriculantService().findMatriculantById(idMatriculant);
			User user = serviceFactory.getUserService().findUserByIdUser(idUser);
			List<Specialty> specialties = serviceFactory.getSpecialtyService().getAllGroupBySpecialtyName();
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(matriculant.getIdSpecialty());
			List<SpecialtyHasSubject> specialtyHasSubjects = serviceFactory.getSpecialtyHasSubjectService().getAllBySpecialtyId(specialty.getId());
			List<Exam> exams = serviceFactory.getExamService().getAllExamsByIdMatriculant(matriculant.getId());
			
			request.setAttribute(FormParameter.USER_DATA, user);
			request.setAttribute(FormParameter.MATRICULANT, matriculant);
			request.setAttribute(FormParameter.SPECIALTIES, specialties);
			request.setAttribute(FormParameter.SPECIALTY, specialty);
			request.setAttribute(FormParameter.SUBJECTS, subjectsDB);
			request.setAttribute(FormParameter.COUNT_SUBJECTS, countSubjects);
			
			for (SpecialtyHasSubject has : specialtyHasSubjects) {
				subjectsDB.add(serviceFactory.getSubjectService().findSubjectById(has.getIdSubject()));
			}
			
			if (exams != null) {
				for (Exam ex : exams) {
					flag = serviceFactory.getExamService().remove(ex);
					
					if (flag) {
						matriculant.setActivated(false);
						
						flag = serviceFactory.getMatriculantService().update(matriculant);
						
						if (flag) {
							request.setAttribute(MessageParameter.SUCCESS_DEACTIVATE_MATRICULANT, MessageParameter.SUCCESS_DEACTIVATE_MATRICULANT);
						} else {
							request.setAttribute(MessageParameter.UNSUCCESS_DEACTIVATE_MATRICULANT, MessageParameter.UNSUCCESS_DEACTIVATE_MATRICULANT);
						}
					} else {
						request.setAttribute(MessageParameter.UNSUCCESS_REMOVE_MATRICULANT, MessageParameter.UNSUCCESS_REMOVE_MATRICULANT);
					}
				}	
			} else {
				request.setAttribute(ValidateParameter.EMPTY_FACULTY, ValidateParameter.EMPTY_FACULTY);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in DEACTIVATE_MATRICULANT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
