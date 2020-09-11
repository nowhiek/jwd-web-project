package by.epam.jwd.controller.command.impl.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jwd.controller.parameter.JSPPageName;

public interface Command {
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	default void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        
        if (requestDispatcher != null) {
            requestDispatcher.forward(request, response);
        } else {
        	response.sendRedirect(JSPPageName.ERROR_PAGE);
        }
    }
}
