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
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowUpdateMatriculant implements Command {

	private static Logger logger = LogManager.getLogger(ShowUpdateMatriculant.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		String page = JSPPageName.MATR_PAGE;
	
		int idMatriculant = Integer.parseInt(request.getParameter(FormParameter.ID_MATRICULANT).toString());
		
		List<Subject> subjects = new ArrayList<Subject>();
		
		try {
			Matriculant matriculant = serviceFactory.getMatriculantService().findMatriculantById(idMatriculant);
			
			if (matriculant != null) {
				int countSubjects = serviceFactory.getSpecialtyHasSubjectService().getCountSubjectsByIdSpecialty(matriculant.getIdSpecialty());
				User user = serviceFactory.getUserService().findUserByIdUser(matriculant.getIdUser());
				List<Specialty> specialties = serviceFactory.getSpecialtyService().getAllGroupBySpecialtyName();
				List<Exam> exams = serviceFactory.getExamService().getAllExamsByIdMatriculant(matriculant.getId());
				List<SpecialtyHasSubject> has = serviceFactory.getSpecialtyHasSubjectService().getAllBySpecialtyId(matriculant.getIdSpecialty());
				Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(matriculant.getIdSpecialty());
				
				if (!has.isEmpty()) {
					for (SpecialtyHasSubject h : has) {
						subjects.add(serviceFactory.getSubjectService().findSubjectById(h.getIdSubject()));
					}
				} else {
					request.setAttribute(ValidateParameter.EMPTY_HAS, ValidateParameter.EMPTY_HAS);
				}
				
				request.setAttribute(FormParameter.COUNT_SUBJECTS, countSubjects);
				request.setAttribute(FormParameter.SPECIALTY, specialty);
				request.setAttribute(FormParameter.MATRICULANT, matriculant);
				request.setAttribute(FormParameter.USER_DATA, user);
				request.setAttribute(FormParameter.SPECIALTIES, specialties);
				request.setAttribute(FormParameter.EXAMS, exams);
				request.setAttribute(FormParameter.SUBJECTS, subjects);
			} else {
				request.setAttribute(ValidateParameter.EMPTY_MATRICULANT, ValidateParameter.EMPTY_MATRICULANT);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in SHOW_UPDATE_MATRICULANT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
