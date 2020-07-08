package by.epam.jwd.dao.connectionpool.factory;

public final class ConnectionPoolFactory {
	
	private final static ConnectionPoolFactory instance = new ConnectionPoolFactory();
	
	private ConnectionPoolFactory() {
	}
	
	public static ConnectionPoolFactory getInstance() {
		return instance;
	}
}
