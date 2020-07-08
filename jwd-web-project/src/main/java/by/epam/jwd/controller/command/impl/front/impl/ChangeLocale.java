package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;

public class ChangeLocale implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String local = request.getParameter(FormParameter.LOCALE);
        session.setAttribute(FormParameter.LOCALE, local);

        String command = (String) session.getAttribute(FormParameter.COMMAND_NAME);

        if (command != null) {
            response.sendRedirect(request.getContextPath() + "/Controller?command=" + command);
        } else {
            response.sendRedirect(request.getContextPath());
        }
	}
}
