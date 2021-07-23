package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private String jdbcURL;
	private String jdbcUser;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public ConexionBD(String jdbcURL, String jdbcUser, String jdbcPassword) {
		
		this.jdbcURL = jdbcURL;
		this.jdbcUser = jdbcUser;
		this.jdbcPassword = jdbcPassword;
		
	}
	
	public void conectar() throws SQLException {
		
		if (jdbcConnection==null || jdbcConnection.isClosed()) {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
			
		}
		
	}
	
	public void desconectar() throws SQLException {
		if (jdbcConnection!=null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public Connection getJdbcConecction() {
		return jdbcConnection;
	}
	
	
	
}
