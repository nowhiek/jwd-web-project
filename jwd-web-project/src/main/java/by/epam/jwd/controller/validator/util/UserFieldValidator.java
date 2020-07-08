package by.epam.jwd.controller.validator.util;

public interface UserFieldValidator<K> {
	
	boolean validate(K field);
}
