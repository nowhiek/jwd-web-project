package by.epam.jwd.dao;

import by.epam.jwd.bean.Plan;
import by.epam.jwd.dao.exception.DAOException;

public interface PlanDAO {
	Plan getPlanById(int id) throws DAOException;
	Plan getPlanByCountPlaces(int count) throws DAOException;
	boolean update(Plan plan) throws DAOException;
	boolean create(Plan plan) throws DAOException;
	boolean remove(Plan plan) throws DAOException;
}
