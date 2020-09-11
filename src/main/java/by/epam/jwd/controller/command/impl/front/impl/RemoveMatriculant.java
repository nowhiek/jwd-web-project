package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.MatriculantService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class RemoveMatriculant implements Command {

	private static Logger logger = LogManager.getLogger(RemoveMatriculant.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MatriculantService serviceImpl = serviceFactory.getMatriculantService();
		
		String page = JSPPageName.MATRICULANT_PAGE;

		HttpSession session = request.getSession();
		
		int idUser = Integer.parseInt(session.getAttribute(SessionParameter.ID_USER).toString());
		
		try {
			boolean flag = false;

			Matriculant matriculant = serviceImpl.findMatriculantByIdUser(idUser);
			UserDetail detail = serviceFactory.getUserDetailService().findUserDetailByIdUser(idUser);
			
			if (matriculant != null) {
				flag = serviceImpl.remove(matriculant);
				
				List<Faculty> faculties = serviceFactory.getFacultyService().getAll();
				List<TypeStudy> types = serviceFactory.getTypeStudyService().getAll();
						
				if (flag) {
					request.setAttribute(MessageParameter.SUCCESS_REMOVE_MATRICULANT, MessageParameter.SUCCESS_REMOVE_MATRICULANT);		
					request.setAttribute(FormParameter.USER_DETAIL, detail);
					request.setAttribute(FormParameter.FACULTIES, faculties);
					request.setAttribute(FormParameter.TYPES, types);
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_REMOVE_MATRICULANT, MessageParameter.UNSUCCESS_REMOVE_MATRICULANT);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in REMOVE_MATRICULANT method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
