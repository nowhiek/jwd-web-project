package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.MatriculantService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ShowMatriculant implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MatriculantService serviceImpl = serviceFactory.getMatriculantService();
		
		String page = JSPPageName.MATRICULANT_PAGE;
		
		HttpSession session = request.getSession();
		
		try {
			int idUser = Integer.parseInt(session.getAttribute(SessionParameter.ID_USER).toString());
			
			Matriculant findMatriculantlByIdUser = serviceImpl.findMatriculantByIdUser(idUser);
				
			if (findMatriculantlByIdUser != null) {
				Specialty specialty = serviceFactory.getSpecialtyService().findSpecialtyById(findMatriculantlByIdUser.getIdSpecialty());
				Faculty faculty = serviceFactory.getFacultyService().findFacultyById(specialty.getIdFaculty());
				TypeStudy type = serviceFactory.getTypeStudyService().findTypeStudyById(specialty.getIdTypeStudy());
				
				
				session.setAttribute(FormParameter.TYPE_STUDY, type.getStudyName());
				session.setAttribute(FormParameter.FACULTY, faculty.getFacultyName());
				session.setAttribute(FormParameter.SPECIALTY, specialty.getSpecialtyName());
				session.setAttribute(FormParameter.CERTIFICATE, findMatriculantlByIdUser.getCertificate());
			} else { 
				List<Specialty> specialties = serviceFactory.getSpecialtyService().getAll();
				List<Faculty> faculties = serviceFactory.getFacultyService().getAll();
				List<TypeStudy> types = serviceFactory.getTypeStudyService().getAll();
				
				session.setAttribute(FormParameter.SPECIALTIES, specialties);
				session.setAttribute(FormParameter.FACULTIES, faculties);
				session.setAttribute(FormParameter.TYPES, types);
				
				session.removeAttribute(FormParameter.TYPE_STUDY);
		        session.removeAttribute(FormParameter.FACULTY);
		        session.removeAttribute(FormParameter.SPECIALTY);
		        session.removeAttribute(FormParameter.CERTIFICATE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e.getStackTrace());
			page = JSPPageName.ERROR_PAGE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
	}
}
