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
import by.epam.jwd.controller.validator.ExamValidator;
import by.epam.jwd.controller.validator.ValidatorFactoryImpl;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ActivateMatriculant implements Command {

	private static Logger logger = LogManager.getLogger(ActivateMatriculant.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ExamValidator validator = ValidatorFactoryImpl.getInstance().getExamValidator();
		
		String page = JSPPageName.MATR_PAGE;
		
		int idUser = Integer.parseInt(request.getParameter(FormParameter.USER_ID).toString());
		int idMatriculant = Integer.parseInt(request.getParameter(FormParameter.ID_MATRICULANT).toString());
		int countSubjects = Integer.parseInt(request.getParameter(FormParameter.COUNT_SUBJECTS).toString());
		
		String[] subjectsParam = request.getParameterValues(FormParameter.EXAM_SUBJECT);
		String[] marksParam = request.getParameterValues(FormParameter.EXAM_MARK);
		 
		List<Subject> subjects = new ArrayList<Subject>();
		List<Exam> exams = new ArrayList<Exam>();
		
		try {
			boolean flag = false;
			
			User user = serviceFactory.getUserService().findUserByIdUser(idUser);
			Matriculant matriculant = serviceFactory.getMatriculantService().findMatriculantById(idMatriculant);
			countSubjects = serviceFactory.getSpecialtyHasSubjectService().getCountSubjectsByIdSpecialty(matriculant.getIdSpecialty());
			List<Specialty> specialties  = serviceFactory.getSpecialtyService().getAllGroupBySpecialtyName();
			Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(matriculant.getIdSpecialty());
			List<SpecialtyHasSubject> specialtyHasSubjects = serviceFactory.getSpecialtyHasSubjectService().getAllBySpecialtyId(specialty.getId());
			
			for (SpecialtyHasSubject h : specialtyHasSubjects) {
				subjects.add(serviceFactory.getSubjectService().findSubjectById(h.getIdSubject()));
			}

			request.setAttribute(FormParameter.USER_DATA, user);
			request.setAttribute(FormParameter.MATRICULANT, matriculant);
			request.setAttribute(FormParameter.SPECIALTIES, specialties);
			request.setAttribute(FormParameter.SPECIALTY, specialty);
			request.setAttribute(FormParameter.SUBJECTS, subjects);
			request.setAttribute(FormParameter.COUNT_SUBJECTS, countSubjects);

			List<String> validation = new ArrayList<String>();
			
			if (subjectsParam != null) {
				if (subjectsParam.length == countSubjects) {
					for (int i = 0; i < subjectsParam.length; i++) {
						Subject sub = serviceFactory.getSubjectService().findSubjectByName(subjectsParam[i]);
						SpecialtyHasSubject has = serviceFactory.getSpecialtyHasSubjectService().findSpecialtyHasSubjectByIdSubject(sub.getId());
						
						Exam exam = new Exam(1, idUser, idMatriculant, has.getId(), !marksParam[i].isEmpty() ? Integer.parseInt(marksParam[i]) : -1);
						
						validation.addAll(validator.validate(exam));
						exams.add(exam);
					}
					
					if (validation.size() == 0) {
						for (Exam ex : exams) {
							flag = serviceFactory.getExamService().create(ex);
							
							if (flag) {
								Matriculant currentMatriculant = serviceFactory.getMatriculantService().findMatriculantById(idMatriculant);
								currentMatriculant.setActivated(true);
								
								flag = serviceFactory.getMatriculantService().update(currentMatriculant);
								
								if (flag) {
									request.setAttribute(MessageParameter.SUCCESS_ACTIVATE_MATRICULANT, MessageParameter.SUCCESS_ACTIVATE_MATRICULANT);
									request.setAttribute(FormParameter.EXAMS, exams);
								} else {
									request.setAttribute(MessageParameter.UNSUCCESS_ACTIVATE_MATRICULANT, MessageParameter.UNSUCCESS_ACTIVATE_MATRICULANT);
								}
							} else {
								request.setAttribute(MessageParameter.UNSUCCESS_ADD_EXAM, MessageParameter.UNSUCCESS_ADD_EXAM);
							}
						}
					} else {
						validator.getValidation(request, validation);
					}
				} else {
					request.setAttribute(ValidateParameter.NOT_VALID_COUNT_SUBJECTS, ValidateParameter.NOT_VALID_COUNT_SUBJECTS);
				}
			} else {
				request.setAttribute(ValidateParameter.EMPTY_SUBJECTS, ValidateParameter.EMPTY_SUBJECTS);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in ACTIVATE_MATRICULANT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
