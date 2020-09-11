package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.JSPPageName;


public class WrongMethod implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		response.sendRedirect(JSPPageName.ERROR_PAGE);
	}
}
