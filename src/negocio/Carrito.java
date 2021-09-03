package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Libro;

// clase de objetos Carrito
public class Carrito {
	/**
	 * Atributo Objeto Libro sin iniciar
	 */
	
	Libro libro= null;
	
	
	
	
	/**
	 * Constructor vacio
	 */
	public  Carrito(){
		
		
		
	}
	
	/**
	 * Metodo para agregar un libro al carrito
	 * @param id
	 * @param usuario
	 */
	public  void AgregarLibro(int id, String usuario) {
		// si el atributo Libro no esta iniciado lo inicia
		if (this.libro == null) {
			libro= new Libro();
		}
		
		// se busca el libro en la base de datos por el id y se guarda en el atributo Libro
		this.libro = Libro.buscarId(id);
		// una vez encontrado se borra dicho Libro de la tabla Libros
		Libro.borrar(id);
		try {
		// inserto el Libro junto al Usuario que me pasa la vista en el Carrito	
			daoCarrito.getInstance().insert(libro,usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * metodo para eliminar Libro del carrito y devolverlo a la tabla Libros
	 * @param id
	 */
	public void eliminarLibro(int id) {
		
		try {
			// guardo el Libro que busco en el carrito con el Id
			this.libro= daoCarrito.getInstance().findId(id);
		    // elimino el libro del carrito 
			daoCarrito.getInstance().delete(id);
			// guardo el libro el la tabla Libros de la BD
			libro.guardar();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		
		
	}
	
	/**
	 * metodo para eliminar todos lo libros de un usuario del carrito
	 * @param user
	 */
	public static void eliminarVentaUsuario(String user) {
		
		try {
			// llamamos al metodoa deleteAll() de la clase dao y le pasamos el usuario
			daoCarrito.getInstance().deleteAll(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * metodo para buscar un libro por id en el carrito
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static Libro buscarId (int id) throws SQLException {
	
		// guardamos el libro encontrado mediante el metodo findID() de la clase Dao y lo retornamos
		Libro l= daoCarrito.getInstance().findId(id);
		return l;
		
		
		
	}
	/**
	 * metodo para retornar el importe de los libros que estan el carrito de un usuario
	 * @param usuario
	 * @return
	 * @throws SQLException
	 */
	public double importe(String usuario) throws SQLException {
		
		
		// recogemos el importe de los libros que estan el carrito de un usuario con el metodo totalImporte()
		double precio = daoCarrito.getInstance().totalImporte(usuario);
		
		
		
		// retornamos el Importe
		return precio;
		
		
		
		
	}
	/**
	 * metodo para retornar los libros que tiene un usuario en el carrito 
	 * @param Usuario
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Libro> FindByUsuario(String Usuario) throws SQLException {
		
		// guardamos en un list los libros que tiene un usuario en el carrito con el metodo findUsuario()
		ArrayList<Libro>libros = daoCarrito.getInstance().findUsuario(Usuario);
		
		
		// retornamos el list
		return libros;
	}
	

}
