package by.epam.jwd.dao.factory.impl;

import by.epam.jwd.dao.FacultyDAO;
import by.epam.jwd.dao.MatriculantDAO;
import by.epam.jwd.dao.PlanDAO;
import by.epam.jwd.dao.ResultDAO;
import by.epam.jwd.dao.SpecialtyDAO;
import by.epam.jwd.dao.TypeStudyDAO;
import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.UserDetailDAO;
import by.epam.jwd.dao.factory.DAOFactory;
import by.epam.jwd.dao.impl.FacultyDAOImpl;
import by.epam.jwd.dao.impl.MatriculantDAOImpl;
import by.epam.jwd.dao.impl.PlanDAOImpl;
import by.epam.jwd.dao.impl.ResultDAOImpl;
import by.epam.jwd.dao.impl.SpecialtyDAOImpl;
import by.epam.jwd.dao.impl.TypeStudyDAOImpl;
import by.epam.jwd.dao.impl.UserDAOImpl;
import by.epam.jwd.dao.impl.UserDetailDAOImpl;

public class SqlDAOFactory implements DAOFactory {

	private static final SqlDAOFactory instance = new SqlDAOFactory();
	
	private static final UserDAO userDAO = new UserDAOImpl();
	private static final UserDetailDAO userDetailDAO = new UserDetailDAOImpl();
	private static final FacultyDAO facultyDAO = new FacultyDAOImpl();
	private static final PlanDAO planDAO = new PlanDAOImpl();
	private static final SpecialtyDAO specialtyDAO = new SpecialtyDAOImpl();
	private static final TypeStudyDAO typeStudyDAO = new TypeStudyDAOImpl();
	private static final MatriculantDAO matriculantDAO = new MatriculantDAOImpl();
	private static final ResultDAO resultDAO = new ResultDAOImpl();
	//other

	public static SqlDAOFactory getInstance() {
		return instance;
	}
	
	@Override
	public UserDAO getUserDAO() {
		return userDAO;
	}

	@Override
	public UserDetailDAO getUserDetailDAO() {
		return userDetailDAO;
	}

	@Override
	public FacultyDAO getFacultyDAO() {
		return facultyDAO;
	}

	@Override
	public PlanDAO getPlanDAO() {
		return planDAO;
	}

	@Override
	public SpecialtyDAO getSpecialtyDAO() {
		return specialtyDAO;
	}

	@Override
	public TypeStudyDAO getTypeStudyDAO() {
		return typeStudyDAO;
	}

	@Override
	public MatriculantDAO getMatriculantDAO() {
		return matriculantDAO;
	}
	
	@Override
	public ResultDAO getResultDAO() {
		return resultDAO;
	}
}
