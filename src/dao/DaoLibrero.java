package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Librero;

import singleton.DbConnection;


public class DaoLibrero {
	
	
	

	private Connection conn= null;
	private static DaoLibrero instance = null;
	
	/**
	 * constructor que obtiene un objeto Conection mediante el metodo
	 * getConnection de la clase  DbConnection
	 * 
	 * @throws SQLException
	 */
	private DaoLibrero() throws SQLException {
		
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
	 
	
	public static DaoLibrero getInstance() throws SQLException {
		
		if(instance == null) {
		
		instance = new DaoLibrero();
		
		}
		
		return instance;
	}
	
/**
 * Metodo que inserta un Librero en la BD
 * @param librero
 */
public void insert(Librero librero) {
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT into librero(login,password) VALUE(?, ?)");
			                                           
			ps.setString(1, librero.getLogin());
			ps.setString(2, librero.getPasword());
			
			
			
			int insertados = ps.executeUpdate();
			if(insertados== 1) {
				System.out.println("Librero insertado correctamente");
				
			ps.close();	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
/**
 * Metodo que borra un librero de la BD
 * @param id
 */
public void delete(int id) {
	
	
	try {
		PreparedStatement ps = conn.prepareStatement("DELETE from librero WHERE id= ?");
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
 * Metodo que busca y  retorna un Librero por su login 
 * @param login
 * @return
 */
public  Librero findLogin(String login) {
	Librero l = null;
	try {
		PreparedStatement ps = conn.prepareStatement("SELECT* FROM librero WHERE login = ? ");
		
		ps.setString(1, login);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			
			l = new Librero(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
			
		}
		
		rs.close();
		ps.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	return l;
}
/**
 * metodoa que actualiza el password  y login de un librero
 * @param id
 * @param login
 * @param password
 * @throws SQLException
 */
public void  updatePassword(int id , String login, String password) throws SQLException {
	                                               
	  PreparedStatement ps = conn.prepareStatement("UPDATE librero SET login = ?, password = ? WHERE id = ?");
			  
	  ps.setString(1, login);
	  ps.setString(2, password);
	  ps.setInt(3, id);
	  
	  int insert = ps.executeUpdate();
	  
	  if(insert ==1) {
		  
		  System.out.println("Registro modificado correctamente");
	  }
	  
	  ps.close();
	  
}
/**
 * Metodo que retorna un List de libreros de la BD
 * @return
 */
public ArrayList<Librero> listLibreros(){
	ArrayList <Librero >l = null;
			
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("SELECT * FROM librero");
		
	ResultSet rs = ps.executeQuery();
	
	
	
	if (rs.next()) {
		
	
	while(rs.next()) {
		if(l == null) {
			
			l = new ArrayList<>();
			}
		l.add(new Librero(rs.getInt("id"),rs.getString("login"),rs.getString("password")));
		
	
		
	}
	}else {
		JOptionPane.showMessageDialog(null,"no Hay ningun registro en la BBDD");
		
	}
	
	rs.close();
	ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	

	

	return  l;
}
	
	
	

}
