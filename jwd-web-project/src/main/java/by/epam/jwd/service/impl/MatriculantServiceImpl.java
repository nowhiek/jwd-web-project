package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.dao.MatriculantDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.MatriculantService;
import by.epam.jwd.service.exception.ServiceException;

public class MatriculantServiceImpl implements MatriculantService {

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private MatriculantDAO matriculantDAO = sqlDAOFactory.getMatriculantDAO();
	
	@Override
	public List<Matriculant> getAll() throws ServiceException {
		List<Matriculant> list;
		
		try {
			list = matriculantDAO.getAll();
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_ALL : MATRICULANT_DAO]");
		}
		
		return list;
	}

	@Override
	public boolean create(Matriculant matriculant) throws ServiceException {
		boolean flag;
		
		try {
			flag = matriculantDAO.create(matriculant);
		} catch (DAOException e) {
			throw new ServiceException("I can't create the matriculant. [CREATE : MATRICULANT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean update(Matriculant matriculant) throws ServiceException {
		boolean flag;
		
		try {
			flag = matriculantDAO.update(matriculant);
		} catch (DAOException e) {
			throw new ServiceException("I can't update the matriculant. [UPDATE : MATRICULANT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Matriculant matriculant) throws ServiceException {
		boolean flag;
		
		try {
			flag = matriculantDAO.remove(matriculant);
		} catch (DAOException e) {
			throw new ServiceException("I can't remove the matriculant. [REMOVE : MATRICULANT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public Matriculant findMatriculantById(int id) throws ServiceException {
		Matriculant matriculant = null;
		
        try {
        	matriculant = matriculantDAO.getMatriculantById(id);
        } catch (DAOException e) {
            throw new ServiceException("I can't find the matriculant by id. [FIND_MATRICULANT_BY_ID : MATRICULANT_DAO]");
        }
        
		return matriculant;
	}

	@Override
	public Matriculant findMatriculantByIdUser(int idUser) throws ServiceException {
		Matriculant matriculant = null;
		
        try {
        	matriculant = matriculantDAO.getMatriculantByUser(idUser);
        } catch (DAOException e) {
            throw new ServiceException("I can't find the matriculant by id user. [FIND_MATRICULANT_BY_ID_USER : MATRICULANT_DAO]");
        }
        
		return matriculant;
	}

}
