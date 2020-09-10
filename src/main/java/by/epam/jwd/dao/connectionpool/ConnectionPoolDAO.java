package by.epam.jwd.dao.connectionpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import by.epam.jwd.dao.connectionpool.exception.ConnectionPoolException;

public interface ConnectionPoolDAO {

	void initPoolData() throws ConnectionPoolException;
    void dispose();
    Connection takeConnection() throws ConnectionPoolException;
    void closeConnection(Connection connection, Statement statement, ResultSet set);
    void closeConnection(Connection connection, Statement statement);
}
