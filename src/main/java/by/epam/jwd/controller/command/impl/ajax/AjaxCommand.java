package by.epam.jwd.controller.command.impl.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AjaxCommand {
	
	String execute(HttpServletRequest request, HttpServletResponse response);
}
