package by.epam.jwd.dao.factory;

import by.epam.jwd.dao.factory.impl.SqlDAOFactory;

public class DAOFactoryUtil {

	private static final DAOFactoryUtil instance = new DAOFactoryUtil();
	private static final DAOFactory sqlDAOFactory = SqlDAOFactory.getInstance();
	
	private DAOFactoryUtil() {
	}
	
	public static DAOFactoryUtil getInstance() {
		return instance;
	}
	
	public static DAOFactory getSQLDAOFactory() {
		return sqlDAOFactory;
	}
}
