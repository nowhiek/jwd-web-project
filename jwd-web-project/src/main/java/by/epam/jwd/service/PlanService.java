package by.epam.jwd.service;

import by.epam.jwd.bean.Plan;
import by.epam.jwd.service.exception.ServiceException;

public interface PlanService {
	boolean create(Plan plan) throws ServiceException;
	boolean update(Plan plan) throws ServiceException;
	boolean remove(Plan plan) throws ServiceException;
	Plan findPlanByCountPages(int count) throws ServiceException;
	Plan findPlanById(int idPlan) throws ServiceException;
}
