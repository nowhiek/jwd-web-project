package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.Matriculant;
import by.epam.jwd.bean.Plan;
import by.epam.jwd.bean.Specialty;
import by.epam.jwd.dao.MatriculantDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.MatriculantService;
import by.epam.jwd.service.exception.MatriculantServiceException;
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
			throw new MatriculantServiceException("DAOException in [GET_ALL : MATRICULANT_DAO]", e);
		}
		
		return list;
	}
	
	@Override
	public List<Matriculant> getAllMatriculantsBySpecialty(Specialty specialty) throws ServiceException {
		List<Matriculant> list;
		
		try {
			list = matriculantDAO.getAllMatriculantsBySpecialty(specialty);
		} catch(DAOException e) {
			throw new MatriculantServiceException("DAOException in [GET_ALL_MATRICULANTS_BY_SPECIALTY : MATRICULANT_DAO]", e);
		}
		
		return list;
	}
	
	@Override
	public List<Matriculant> getListMatriculantsBySpecialty(Specialty specialty, Plan plan) throws ServiceException {
		List<Matriculant> list;
		
		try {
			list = matriculantDAO.getListMatriculantsBySpecialty(specialty, plan);
		} catch(DAOException e) {
			throw new MatriculantServiceException("DAOException in [GET_LIST_MATRICULANTS_BY_SPECIALTY : MATRICULANT_DAO]", e);
		}
		
		return list;
	}
	
	@Override
	public List<Matriculant> getAllMatriculantsByProperty(boolean active) throws ServiceException {
		List<Matriculant> list;
		
		try {
			list = matriculantDAO.getAllMatriculantsByProperty(active);
		} catch(DAOException e) {
			throw new MatriculantServiceException("DAOException in [GET_ALL_MATRICULANTS_BY_PROPERTY : MATRICULANT_DAO]", e);
		}
		
		return list;
	}

	@Override
	public boolean create(Matriculant matriculant) throws ServiceException {
		boolean flag;
		
		try {
			flag = matriculantDAO.create(matriculant);
		} catch (DAOException e) {
			throw new MatriculantServiceException("DAOException in [CREATE : MATRICULANT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean update(Matriculant matriculant) throws ServiceException {
		boolean flag;
		
		try {
			flag = matriculantDAO.update(matriculant);
		} catch (DAOException e) {
			throw new MatriculantServiceException("DAOException in [UPDATE : MATRICULANT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Matriculant matriculant) throws ServiceException {
		boolean flag;
		
		try {
			flag = matriculantDAO.remove(matriculant);
		} catch (DAOException e) {
			throw new MatriculantServiceException("DAOException in [REMOVE : MATRICULANT_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public Matriculant findMatriculantById(int id) throws ServiceException {
		Matriculant matriculant = null;
		
        try {
        	matriculant = matriculantDAO.getMatriculantById(id);
        } catch (DAOException e) {
			throw new MatriculantServiceException("DAOException in [FIND_MATRICULANT_BY_ID : MATRICULANT_DAO]", e);
        }
        
		return matriculant;
	}

	@Override
	public Matriculant findMatriculantByIdUser(int idUser) throws ServiceException {
		Matriculant matriculant = null;
		
        try {
        	matriculant = matriculantDAO.getMatriculantByUser(idUser);
        } catch (DAOException e) {
			throw new MatriculantServiceException("DAOException in [FIND_MATRICULANT_BY_ID_USER : MATRICULANT_DAO]", e);
        }
        
		return matriculant;
	}
}
