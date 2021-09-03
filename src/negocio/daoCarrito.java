package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoLibro;
import modelo.Libro;
import singleton.DbConnection;

public class daoCarrito {
	
	// Atributos Connection y instancia estatica de Dao Carrito si iniciar
	private Connection conn= null;
	private static daoCarrito instance = null;
	
	// constructor privado que inicia el atributo  conexion con el metodo GetConnection()
	private daoCarrito() throws SQLException {
		
	conn= DbConnection.getConnection();
	}
	
	
/**
	 * Metodo estatico ya que al no poder crear instancias de la clase y ser el constructor Privado 
	 * este metodo inicia la instancia "Instance" de tipo estatica la cual al iniciarse a su vez
	 * inicia una Variable Connection.
	 * Retorna esta misma instancia lo cual te da opcion a acceder a todos lo metodos de la clase 
	 * @return
	 * @throws SQLException
	 */
public static daoCarrito getInstance() throws SQLException {
		
		if(instance == null) {
		
		instance = new daoCarrito();
		
		}
		
		return instance;
	}
	


	
	/**
	 * Metodo para insertar un Libro en la tabla carrito
	 * @param libro
	 * @param usuario
	 */
	public void insert(Libro libro, String usuario) {
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT into carrito(nombre, isnb, precio, autor, usuario) VALUE(?, ?, ?, ?, ?)");
			                                           
			ps.setString(1, libro.getNombre());
			ps.setString(2, libro.getIsnb());
			ps.setDouble(3, libro.getPrecio());
			ps.setString(4, libro.getAutor());
			ps.setString(5,usuario);
			
			int insertados = ps.executeUpdate();
			if(insertados== 1) {
				System.out.println("Libro añadido al carrito");
				
			ps.close();	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
/**
 * Metodo para borrar un libro de la tabla carrito
 * @param id
 */
public void delete(int id) {
		
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE from carrito WHERE id= ?");
			ps.setInt(1, id);
			
			int borrado = ps.executeUpdate();
			if(borrado == 1){
				System.out.println("Libro borrado del carrito");
				
			}else{
				System.out.println("Problemas al  borrar libro en el carrito");
				
			}
			
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

/**
 * metodo para elimunar todos los libros de un Usuario de  la tabla carrito
 * @param user
 */
public void deleteAll(String user) {
	
	
	try {
		PreparedStatement ps = conn.prepareStatement("DELETE from carrito WHERE usuario= ?");
		ps.setString(1, user);
		
		int borrado = ps.executeUpdate();
		if(borrado == 1){
			System.out.println("Venta de  Usuario borrado del carrito");
			
		}else{
			System.out.println("Problemas al  borrar Ventas  en el carrito");
			
		}
		
		
		ps.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
}
/**
 * metodo para encontrar un Libro en la tabla carrito y retornarlo al controlador
 * @param id
 * @return
 */
public  Libro findId(int id) {
	Libro libro = null;
	try {
		PreparedStatement ps = conn.prepareStatement("SELECT* FROM carrito WHERE id = ? ");
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			
			libro = new Libro( rs.getInt("id"),rs.getString("nombre"),rs.getString("isnb"),rs.getDouble("precio"),rs.getString("autor"));
				
		}
		
		rs.close();
		ps.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	return libro;
}
	
/**
 * metodo para retrornar al modelo un list de Libros de un Usuario de la tabla Carrito
 * @param usuario
 * @return
 */
public  ArrayList<Libro> findUsuario(String usuario) {
	
	

	Libro libro = null;
	ArrayList<Libro>libros = null;
	try {
		PreparedStatement ps = conn.prepareStatement("SELECT* FROM carrito WHERE Usuario = ? ");
		
		ps.setString(1, usuario);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			
			if (libros == null ) {
				libros = new ArrayList<Libro>();
			}
			
			
			libro = new Libro(rs.getInt("id"),rs.getString("nombre"),rs.getString("isnb"),rs.getDouble("precio"),rs.getString("autor"));
			libros.add(libro);
		}
		
		rs.close();
		ps.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	return libros;
	

	
}
/**
 * metodo para retornar el importe total de los libros de un usuario de la tabla carrito
 * @param usuario
 * @return
 * @throws SQLException
 */
public  double totalImporte(String usuario) throws SQLException {

	    // hacemos un select de todos los libros de un usario que nos retorna un Resultset con ellos
		PreparedStatement ps = conn.prepareStatement("SELECT* FROM carrito WHERE Usuario = ? ");
		
		ps.setString(1, usuario);
		// variable double para guardar el importe
		double total= 0;
		// generamos el result set
		ResultSet rs = ps.executeQuery();
		
		// iteramos el Resultset
		while(rs.next()){
		
			// es cada iteracion le sumamos el valor del libro a "total"
			total = total + rs.getDouble("precio");
			
		}
		
		rs.close();
		ps.close();
		
	

	// retornamos total
	return total;
	

	
}
	

}

