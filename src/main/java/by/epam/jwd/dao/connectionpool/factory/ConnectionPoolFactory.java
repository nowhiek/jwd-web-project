package by.epam.jwd.dao.connectionpool.factory;

import by.epam.jwd.dao.connectionpool.impl.SqlConnectionPoolImpl;

public final class ConnectionPoolFactory {
	
	private static final ConnectionPoolFactory instance = new ConnectionPoolFactory();
	
	private final SqlConnectionPoolImpl sqlConnectionPool = new SqlConnectionPoolImpl();
	
	private ConnectionPoolFactory() {
	}
	
	public static ConnectionPoolFactory getInstance() {
		return instance;
	}
	
	public SqlConnectionPoolImpl getSqlConnectionPoolDAO() {
		return sqlConnectionPool;
	}
}
