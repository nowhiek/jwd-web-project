package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.Result;
import by.epam.jwd.dao.ResultDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.ResultService;
import by.epam.jwd.service.exception.ResultServiceException;
import by.epam.jwd.service.exception.ServiceException;

public class ResultServiceImpl implements ResultService {

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private ResultDAO resultDAO = sqlDAOFactory.getResultDAO();
	
	@Override
	public List<Result> getAll() throws ServiceException {
		List<Result> list;
		
		try {
			list = resultDAO.getAll();
		} catch(DAOException e) {
			throw new ResultServiceException("DAOException in [GET_ALL : RESULT_DAO]", e);
		}
		
		return list;
	}

	@Override
	public boolean create(Result result) throws ServiceException {
		boolean flag;
		
		try {
			flag = resultDAO.create(result);
		} catch (DAOException e) {
			throw new ResultServiceException("DAOException in [CREATE : RESULT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean update(Result result) throws ServiceException {
		boolean flag;
		
		try {
			flag = resultDAO.update(result);
		} catch (DAOException e) {
			throw new ResultServiceException("DAOException in [UPDATE : RESULT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Result result) throws ServiceException {
		boolean flag;
		
		try {
			flag = resultDAO.remove(result);
		} catch (DAOException e) {
			throw new ResultServiceException("DAOException in [REMOVE : RESULT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public Result findResultById(int id) throws ServiceException {
		Result result = null;
		
        try {
        	result = resultDAO.getResultById(id);
        } catch (DAOException e) {
        	throw new ResultServiceException("DAOException in [FIND_RESULT_BY_ID : RESULT_DAO]", e);
        }
        
		return result;
	}

	@Override
	public Result findResultByIdMatriculant(int idMatriculant) throws ServiceException {
		Result result = null;
		
        try {
        	result = resultDAO.getResultByMatriculant(idMatriculant);
        } catch (DAOException e) {
        	throw new ResultServiceException("DAOException in [FIND_RESULT_BY_ID_MATRICULANT : RESULT_DAO]", e);
        }
        
		return result;
	}
}
