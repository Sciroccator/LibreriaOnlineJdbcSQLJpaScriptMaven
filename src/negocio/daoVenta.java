package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Libro;
import singleton.DbConnection;

public class daoVenta {
	
	// atributos

private static daoVenta instance = null;
private Connection conn= null;
	 
  // constructor privado que inicia el atributo Connection llamando a la clase DBconnectio y a su metodo GetConnection() 
	private daoVenta() throws SQLException {
		
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
public static daoVenta getInstance() throws SQLException {
		
		if(instance == null) {
		
		instance = new daoVenta();
		
		}
		
		return instance;
	}

	/**
	 * Metodo para insertar una venta en la tabla ventas
	 * @param usuario
	 * @throws SQLException
	 */
	public void insert(String usuario) throws SQLException {
           // recogemos en un list todos lo libros que tiene el usuario en el carrito
		ArrayList<Libro> libros = daoCarrito.getInstance().findUsuario(usuario);
           // si el retorno no es nulo (vamos que el usuario tiene libros )entra en le if
		if (libros != null) {
			// se itera el List de libros 
			for (Libro l : libros) {
                // en cada iteracion se inserta la venta del libro en la tabla ventas 
				PreparedStatement ps = conn
						.prepareStatement("INSERT into ventas(importe, usuario, titulo, autor) VALUE(?, ?, ?, ?)");

				ps.setDouble(1, l.getPrecio());
				ps.setString(2, usuario);
				ps.setString(3, l.getNombre());
				ps.setString(4, l.getAutor());

				int insertados = ps.executeUpdate();
				if (insertados == 1) {
					System.out.println("Libro añadido a venta");

					ps.close();

				}

			}

		}else {// si el usuario no tiene ningun libro en el Carrito se manda una aviso a Consola
			System.out.println("El Usuario "+ usuario +" no tiene ningun libro comprado");
			
		}

	}
	/**
	 * 
	 * Metodo para encontrar todas las venta de un usario 
	 * @param usuario
	 * @return
	 */
	public  ArrayList<Venta> findUsuario(String usuario) {
		
		

		Venta venta = null;
		ArrayList<Venta>ventas = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT* FROM ventas WHERE usuario = ? ");
			
			ps.setString(1, usuario);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				if (ventas == null ) {
					ventas = new ArrayList<Venta>();
				}
				
				
			ventas.add(venta = new Venta(rs.getInt("id"),rs.getDouble("importe"),rs.getString("usuario"),rs.getString("titulo"),rs.getString("autor")));
		
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return ventas;
		

		
	}
	
	/**
	 * metodo para devolcer el importe de las ventas de un usuario
	 * @param usuario
	 * @return
	 * @throws SQLException
	 */
	public  double totalImporte(String usuario) throws SQLException {
		
		

		
		
		
		PreparedStatement ps = conn.prepareStatement("SELECT* FROM ventas WHERE usuario = ? ");
		
		ps.setString(1, usuario);
		double total= 0;
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
		
			
			total = total + rs.getDouble("importe");
			
		}
		
		rs.close();
		ps.close();
		
	

	
	return total;
	

	
}
}
