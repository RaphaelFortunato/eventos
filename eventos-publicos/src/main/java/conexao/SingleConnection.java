package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/eventos";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	
	private static void conexao() {
		
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conectou massa!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public SingleConnection() {
		conexao();
	}
	
	static {
		conexao();
	}
	
	public static Connection getConexao() {
		return connection;
	}
}
