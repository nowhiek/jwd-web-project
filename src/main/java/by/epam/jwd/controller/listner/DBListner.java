package by.epam.jwd.controller.listner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.controller.listner.exception.InitConnectionPoolListnerException;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.dao.connectionpool.factory.ConnectionPoolFactory;;

public class DBListner implements ServletContextListener {
	
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ConnectionPoolFactory.getInstance().getSqlConnectionPoolDAO().initPoolData();
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, "Connection pool can't initialize", e);
			throw new InitConnectionPoolListnerException("I can't init connection pool on Listner.", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPoolFactory.getInstance().getSqlConnectionPoolDAO().dispose();
	}
}
