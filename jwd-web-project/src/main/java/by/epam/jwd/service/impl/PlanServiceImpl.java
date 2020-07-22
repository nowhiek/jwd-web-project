package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.bean.Plan;
import by.epam.jwd.dao.PlanDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.impl.SqlDAOFactory;
import by.epam.jwd.service.PlanService;
import by.epam.jwd.service.exception.ServiceException;

public class PlanServiceImpl implements PlanService {

	private SqlDAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	private PlanDAO planDAO = sqlDAOFactory.getPlanDAO();
	
	@Override
	public List<Plan> getAll() throws ServiceException {
		List<Plan> list;
		
		try {
			list = planDAO.getAll();
		} catch(DAOException e) {
			throw new ServiceException("Change the discription this exception. [GET_ALL : PLAN_DAO]");
		}
		
		return list;
	}
	
	@Override
	public boolean create(Plan plan) throws ServiceException {
		boolean flag;
		
		try {
			flag = planDAO.create(plan);
		} catch (DAOException e) {
			throw new ServiceException("I can't create the plan. [CREATE : PLAN_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean update(Plan plan) throws ServiceException {
		boolean flag;
		
		try {
			flag = planDAO.update(plan);
		} catch (DAOException e) {
			throw new ServiceException("I can't update the plan. [UPDATE : PLAN_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public boolean remove(Plan plan) throws ServiceException {
		boolean flag;
		
		try {
			flag = planDAO.remove(plan);
		} catch (DAOException e) {
			throw new ServiceException("I can't remove the plan. [REMOVE : PLAN_DAO]", e);
		}
		
		return flag;
	}

	@Override
	public Plan findPlanByCountPages(int count) throws ServiceException {
		Plan plan = null;
		
        try {
        	plan = planDAO.getPlanByCountPlaces(count);
        } catch (DAOException e) {
            throw new ServiceException("I can't find the plan by count pages. [GET_PLAN_BY_COUNT_PAGES : PLAN_DAO]");
        }
        
		return plan;
	}

	@Override
	public Plan findPlanById(int idPlan) throws ServiceException {
		Plan plan = null;
		
        try {
        	plan = planDAO.getPlanById(idPlan);
        } catch (DAOException e) {
            throw new ServiceException("I can't find the plan by id. [GET_PLAN_BY_ID : PLAN_DAO]");
        }
        
		return plan;
	}
}
