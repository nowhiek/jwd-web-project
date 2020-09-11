package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.MessageParameter;
import by.epam.jwd.service.MatriculantService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class RemoveApplication implements Command {

	private static Logger logger = LogManager.getLogger(RemoveApplication.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		MatriculantService serviceImpl = serviceFactory.getMatriculantService();
		
		String page = JSPPageName.MATR_PAGE;
		
		int idMatriculant = Integer.parseInt(request.getParameter(FormParameter.ID_MATRICULANT).toString());
		
		try {
			boolean flag = false;
			
			Matriculant matriculant = serviceImpl.findMatriculantById(idMatriculant);
			
			if (matriculant != null) {
				flag = serviceImpl.remove(matriculant);
				
				if (flag) {
					page = JSPPageName.ADMIN_PAGE;
				} else {
					request.setAttribute(MessageParameter.UNSUCCESS_REMOVE_MATRICULANT, MessageParameter.UNSUCCESS_REMOVE_MATRICULANT);
				}
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in REMOVE_APPLICATION method.", e);
			page = JSPPageName.ERROR_PAGE;
		}
		
		forwardToPage(request, response, page);
	}
}
