package by.epam.jwd.controller.validator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

interface Validator {

	default void getValidation(HttpServletRequest request, List<String> validation) {
    	for (String warning : validation) {
			request.setAttribute(warning.toLowerCase(), warning);
		}
    }
}
