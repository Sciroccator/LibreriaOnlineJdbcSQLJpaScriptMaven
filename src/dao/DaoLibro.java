package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Libro;
import singleton.DbConnection;

public class DaoLibro {
	
	
	
	private Connection conn= null;
	private static DaoLibro instance = null;
	
	/**
	 * constructor que inizia el atributo Connection llamando a el metodo getConnection()
	 * de DbConnection
	 * @throws SQLException
	 */
	private DaoLibro() throws SQLException {
		
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
	public static DaoLibro getInstance() throws SQLException {
		
		if(instance == null) {
		
		instance = new DaoLibro();
		
		}
		
		return instance;
	}
	
	/**
	 * metodo que inserta un libro en la base de datos
	 * @param libro
	 */
	public void insert(Libro libro) {
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT into libros(nombre, isnb, precio, autor) VALUE(?, ?, ?, ?)");
			                                           
			ps.setString(1, libro.getNombre());
			ps.setString(2, libro.getIsnb());
			ps.setDouble(3, libro.getPrecio());
			ps.setString(4, libro.getAutor());
			
			int insertados = ps.executeUpdate();
			if(insertados== 1) {
				System.out.println("Libro insertado correctamente");
				
			ps.close();	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	/**
	 * Metodo que Borra un Libro de la base de datos mediente la id
	 * @param id
	 */
	public void delete(int id) {
		
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE from libros WHERE id= ?");
			ps.setInt(1, id);
			
			int borrado = ps.executeUpdate();
			if(borrado == 1){
				System.out.println("Registro borrado de la base de datos");
				
			}else{
				System.out.println("Problemas al  borrar en la base de datos");
				
			}
			
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	/**
	 * metodo que retorna un libro de la BD encontrado mediante el ISNB
	 * @param isnb
	 * @return
	 */
	public  Libro findIsnb(String isnb) {
		Libro libro = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT* FROM libros WHERE isnb = ? ");
			
			ps.setString(1, isnb);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				libro = new Libro(rs.getInt("id"),rs.getString("nombre"),rs.getString("isnb"),rs.getDouble("precio"),rs.getString("autor"));
					
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
	 * Metodo que retorna un libro de la BD encontrado mediante su nombre
	 * @param nombre
	 * @return
	 */
	public  Libro findNombre(String nombre) {
		Libro libro = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT* FROM libros WHERE nombre LIKE ? ");
			
			ps.setString(1,"%%"+nombre+"%%");
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				libro = new Libro(rs.getString("nombre"),rs.getString("isnb"),rs.getDouble("precio"),rs.getString("autor"));
					
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
	 * metodo que retorna un libro de la BD mediante su busqueda por ID
	 * @param id
	 * @return
	 */
	public  Libro findId(int id) {
		Libro libro = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT* FROM libros WHERE id = ? ");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				libro = new Libro(rs.getString("nombre"),rs.getString("isnb"),rs.getDouble("precio"),rs.getString("autor"));
					
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
	 * metodo que retorna todos lo libros de la bd en un list 
	 * @return
	 */
	public ArrayList<Libro> ListLibros(){
		ArrayList<Libro>libros = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT* from libros ");
			ResultSet rs = ps.executeQuery();
			
			
			
			while (rs.next()) {
				
				if(libros == null) {
					
					libros = new ArrayList<Libro>();
					
				}
				libros.add(new Libro(rs.getInt("id"),rs.getString("nombre"),rs.getString("isnb"),rs.getDouble("precio"),rs.getString("autor")))	;
					
			}
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return libros;
		
	}
	 /**
	  * metodo que actualiza el precio de un Libro en BD necesita del id del libro 
	  * y del nuevo precio a actualizar
	 * @param id
	 * @param precio
	 * @throws SQLException
	 */
	public void  updatePrecio(int id , Double precio) throws SQLException {
		  
		  PreparedStatement ps = conn.prepareStatement("UPDATE libros SET precio = ? WHERE id = ?");
				  
		  ps.setDouble(1, precio);
		  ps.setInt(2, id);
		  
		  int insert = ps.executeUpdate();
		  
		  if(insert ==1) {
			  
			  System.out.println("Registro modificado correctamente");
		  }
		  
		  ps.close();
		  
	  }
	
	

}
