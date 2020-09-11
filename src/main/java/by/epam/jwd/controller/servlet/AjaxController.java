package by.epam.jwd.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jwd.controller.command.CommandProvider;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.controller.parameter.FormParameter;

public class AjaxController extends HttpServlet {
	
	private static final long serialVersionUID = 5839950895654233975L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doCommand(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doCommand(request, response);
	}
	
	private void doCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ajaxCommandName = request.getParameter(FormParameter.COMMAND_NAME);
        CommandProvider ajaxCommandProvider = CommandProvider.getInstance();
        AjaxCommand ajaxCommand = ajaxCommandProvider.getAjaxCommand(ajaxCommandName.toUpperCase());

        String jsonAnswer = ajaxCommand.execute(request, response);
        
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonAnswer);

    }
}