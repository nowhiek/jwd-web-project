package by.epam.jwd.dao.connectionpool;

public final class ConnectionPoolManager {
	
	private static final ConnectionPoolManager instance = new ConnectionPoolManager();
	private final ConnectionPool connectionPool = new ConnectionPool();
	
	private ConnectionPoolManager() {}
	
	public static ConnectionPoolManager getInstance() {
		return instance;
	}
	
	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}
}
