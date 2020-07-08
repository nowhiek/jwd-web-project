package by.epam.jwd.controller.listner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import by.epam.jwd.dao.connectionpool.ConnectionPoolManager;
import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.jwd.controller.listner.exception.InitConnectionPoolListnerException;;

public class DBListner implements ServletContextListener {
	
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ConnectionPoolManager.getInstance().getConnectionPool().initPoolData();
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e.getStackTrace());
			throw new InitConnectionPoolListnerException("I can't init connection pool on Listner.", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPoolManager.getInstance().getConnectionPool().dispose();
	}
}
