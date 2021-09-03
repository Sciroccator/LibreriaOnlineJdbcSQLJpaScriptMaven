package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	
	// atributos

	private static final String  JDBC_URL = "jdbc:mysql://localhost:3306/alquiler";
	private static Connection instance= null;
	
	
	
	
	/**
	 * Metodo estatico para poder ser llamado sin necesidad de crear una instancia
	 *  que utiliza los Atributos estaticos de la clase  JDBC_URL e instance
	 *  inicia el atributo instance de tipo connection y le añade el usuario el password de la BD
	 *  y la URL para conectar a la base de datos y retorna esa conexion "instance "
	 * @return
	 * @throws SQLException
	 */
	public static Connection  getConnection() throws SQLException {
		if (instance == null) {
		Properties pro = new Properties();
		pro.put("user", "root");
		pro.put("pass","8009");
		
	instance = DriverManager.getConnection(JDBC_URL, pro.getProperty("user"),pro.getProperty("pass"));
		}
		
		
		
		
		return instance;
	}
	
	
	
	
}
