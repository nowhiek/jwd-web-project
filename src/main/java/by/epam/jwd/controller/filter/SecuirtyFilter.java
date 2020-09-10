package by.epam.jwd.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.jwd.bean.Role;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommandName;
import by.epam.jwd.controller.command.impl.front.CommandName;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.SessionParameter;

public class SecuirtyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		String role = (String)session.getAttribute(SessionParameter.ROLE_USER);
		String command = req.getParameter(FormParameter.COMMAND_NAME);
		
		if ((role == null || role != Role.ADMIN.getTitle()) && (command.equals(CommandName.SHOW_ADMIN_PANEL.toString().toLowerCase())
				|| command.equals(CommandName.CHANGE_USER.toString().toLowerCase())
				|| command.equals(CommandName.ACTIVATE_MATRICULANT.toString().toLowerCase())
				|| command.equals(CommandName.ADD_FACULTY.toString().toLowerCase())
				|| command.equals(CommandName.ADD_PLAN.toString().toLowerCase())
				|| command.equals(CommandName.ADD_SPECIALTY.toString().toLowerCase())
				|| command.equals(CommandName.ADD_SPECIALTY_HAS_SUBJECT.toString().toLowerCase())
				|| command.equals(CommandName.ADD_SUBJECT.toString().toLowerCase())
				|| command.equals(CommandName.ADD_TYPE_STUDY.toString().toLowerCase())
				|| command.equals(CommandName.BLOCK_USER.toString().toLowerCase())
				|| command.equals(CommandName.CREATE_EXCEL_TABLE.toString().toLowerCase())
				|| command.equals(CommandName.CREATE_LIST_BY_SPECIALTY.toString().toLowerCase())
				|| command.equals(CommandName.DEACTIVATE_MATRICULANT.toString().toLowerCase())
				|| command.equals(CommandName.REMOVE_FACULTY.toString().toLowerCase())
				|| command.equals(CommandName.REMOVE_PLAN.toString().toLowerCase())
				|| command.equals(CommandName.REMOVE_SPECIALTY.toString().toLowerCase())
				|| command.equals(CommandName.REMOVE_SPECIALTY_HAS_SUBJECT.toString().toLowerCase())
				|| command.equals(CommandName.REMOVE_SUBJECT.toString().toLowerCase())
				|| command.equals(CommandName.REMOVE_TYPE.toString().toLowerCase())
				|| command.equals(CommandName.REMOVE_APPLICATION.toString().toLowerCase())
				|| command.equals(CommandName.SHOW_UPDATE_FACULTY.toString().toLowerCase())
				|| command.equals(CommandName.SHOW_UPDATE_MATRICULANT.toString().toLowerCase())
				|| command.equals(CommandName.SHOW_UPDATE_PLAN.toString().toLowerCase())
				|| command.equals(CommandName.SHOW_UPDATE_SPECIALTY.toString().toLowerCase())
				|| command.equals(CommandName.SHOW_UPDATE_SPECIALTY_HAS_SUBJECT.toString().toLowerCase())
				|| command.equals(CommandName.SHOW_UPDATE_SUBJECT.toString().toLowerCase())
				|| command.equals(CommandName.SHOW_UPDATE_TYPE.toString().toLowerCase())
				|| command.equals(CommandName.UPDATE_FACULTY.toString().toLowerCase())
				|| command.equals(CommandName.UPDATE_MATRICULANT.toString().toLowerCase())
				|| command.equals(CommandName.UPDATE_PLAN.toString().toLowerCase())
				|| command.equals(CommandName.UPDATE_SPECIALTY.toString().toLowerCase())
				|| command.equals(CommandName.UPDATE_SPECIALTY_HAS_SUBJECT.toString().toLowerCase())
				|| command.equals(CommandName.UPDATE_SUBJECT.toString().toLowerCase())
				|| command.equals(CommandName.UPDATE_TYPE.toString().toLowerCase())
				|| command.equals(CommandName.DEACTIVATE_MATRICULANT.toString().toLowerCase())
				|| command.equals(CommandName.SHOW_USER_DATA.toString().toLowerCase())
				|| command.equals(AjaxCommandName.GET_ALL_MATRICULANTS.toString().toLowerCase())
				|| command.equals(AjaxCommandName.GET_ALL_FACULTIES.toString().toLowerCase())
				|| command.equals(AjaxCommandName.GET_ALL_MATRICULANTS_BY_PROPERTY.toString().toLowerCase())
				|| command.equals(AjaxCommandName.GET_ALL_MATRICULANTS_BY_SPECIALTY.toString().toLowerCase())
				|| command.equals(AjaxCommandName.GET_ALL_PLANS.toString().toLowerCase())
				|| command.equals(AjaxCommandName.GET_ALL_SPECIALTIES.toString().toLowerCase())
				|| command.equals(AjaxCommandName.GET_ALL_SPECIALTY_HAS_SUBJECT.toString().toLowerCase())
				|| command.equals(AjaxCommandName.GET_ALL_SUBJECTS.toString().toLowerCase())
				|| command.equals(AjaxCommandName.GET_ALL_TYPES.toString().toLowerCase())
				|| command.equals(AjaxCommandName.GET_ALL_USERS.toString().toLowerCase()))) {
			req.getRequestDispatcher(JSPPageName.INDEX).forward(req, res);
		} 
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
