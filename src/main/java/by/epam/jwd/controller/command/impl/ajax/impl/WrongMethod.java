package by.epam.jwd.controller.command.impl.ajax.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;

public class WrongMethod implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "{\"status\":\"error\"}";
	}
}
