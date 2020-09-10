package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;

public class SignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
        session.removeAttribute(SessionParameter.ID_USER);
        session.removeAttribute(SessionParameter.NAME_USER);
        session.removeAttribute(SessionParameter.ROLE_USER);
        
        response.sendRedirect(JSPPageName.INDEX);
	}
}
