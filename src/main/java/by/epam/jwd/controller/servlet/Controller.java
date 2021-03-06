package by.epam.jwd.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jwd.controller.command.CommandProvider;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;;

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = -3780069167899943782L;

	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doCommand(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doCommand(request, response);
	}

	private void doCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = null;
		
		commandName = request.getParameter(FormParameter.COMMAND_NAME);
		Command command = CommandProvider.getInstance().getCommand(commandName);
		
		command.execute(request, response);	
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
}
