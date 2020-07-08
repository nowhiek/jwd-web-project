package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.bean.Result;
import by.epam.jwd.service.exception.ServiceException;

public interface ResultService {
	List<Result> getAll() throws ServiceException;
	boolean create(Result result) throws ServiceException;
	boolean update(Result result) throws ServiceException;
	boolean remove(Result result) throws ServiceException;
	Result findResultById(int id) throws ServiceException;
	Result findResultByIdMatriculant(int idMatriculant) throws ServiceException;
}
