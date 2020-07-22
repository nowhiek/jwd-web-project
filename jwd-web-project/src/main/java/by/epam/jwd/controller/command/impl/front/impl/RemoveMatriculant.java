package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.service.MatriculantService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class RemoveMatriculant implements Command {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MatriculantService serviceImpl = serviceFactory.getMatriculantService();
		
		String page = JSPPageName.MATRICULANT_PAGE;
		
		Matriculant matriculant = null;
		
		HttpSession session = request.getSession();
		
		int idUser = Integer.parseInt(session.getAttribute(SessionParameter.ID_USER).toString());
		
		try {
			boolean flag = false;

			matriculant = serviceImpl.findMatriculantByIdUser(idUser);
			
			if (matriculant != null) {
				flag = serviceImpl.remove(matriculant);
			}

			if (flag) {
				request.setAttribute(SessionParameter.SUCCESS_REMOVE_MATRICULANT, SessionParameter.SUCCESS_REMOVE_MATRICULANT);
			} else {
				request.setAttribute(SessionParameter.UNSUCCESS_REMOVE_MATRICULANT, SessionParameter.UNSUCCESS_REMOVE_MATRICULANT);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
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
