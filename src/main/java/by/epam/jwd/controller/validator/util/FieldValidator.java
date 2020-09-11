package by.epam.jwd.controller.validator.util;

public interface FieldValidator<K> {
	
	boolean validate(K field);
}
