package MysqlHelper;

import java.sql.Connection;

public interface IConnection {
	Connection getConnection();
    boolean releaseConnection(Connection connection);
}
