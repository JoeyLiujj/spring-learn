package cn.joey.flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * 实现对象的共享，即共享池，当系统中对象多的时候可以减少内存的开销，通常与工厂模式一起使用
 * 
 * @author Liujj
 */

public class ConnectionPool {
	private Vector<Connection> pool;
	private String url = "jdbc:oracle:thin:@192.168.1.249:1521:orcl";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String username = "tongchou";
	private String password = "tongchoupwd";
	private int poolSize = 100;
	private static ConnectionPool instance = null;
	Connection conn = null;

	public ConnectionPool() {
		pool = new Vector<Connection>(poolSize);
		for (int i = 0; i < poolSize; i++) {
			try {
				Class.forName(driver);
				Connection connection = DriverManager.getConnection(url,
						username, password);
				pool.add(connection);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void release() {
		pool.add(conn);
	}

	public synchronized Connection getConnection() {
		if (poolSize > 0) {
			Connection connection = pool.get(0);
			pool.remove(connection);
			return connection;
		} else {
			return null;
		}
	}
	public static void main(String[] args) {
		ConnectionPool  pool = new ConnectionPool();
		Connection connection = pool.getConnection();
		System.out.println(connection);
	}
}
