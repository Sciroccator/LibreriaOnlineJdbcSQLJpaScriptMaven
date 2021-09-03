package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Usuario;
import singleton.DbConnection;



public class DaoUsuario {
	
	
	/**
	 * Atributos de clase (Singleton)
	 */
	private Connection conn = null;
	private static DaoUsuario instance = null;
	
	/**
	 * constructor que inizia el atributo Connection llamando a el metodo getConnection()
	 * de DbConnection
	 */
	public DaoUsuario() {
		
		try {
			conn= DbConnection.getConnection();
		} catch (SQLException e) {
			System.out.println("Fallo en la conexionn a base de datos");
			
		}
		
	}
	
	/**
	 * Metodo estatico ya que al no poder crear instancias de la clase y ser el constructor Privado 
	 * este metodo inicia la instancia "Instance" de tipo estatica la cual al iniciarse a su vez
	 * inicia una Variable Connection.
	 * Retorna esta misma instancia lo cual te da opcion a acceder a todos lo metodos de la clase 
	 * @return
	 * @throws SQLException
	 */
	public static DaoUsuario getInstance() {
		
		if (instance == null) {
			
			instance = new DaoUsuario();
			
			
			
		}
		
		return instance;
		
		
		
	}
	
	

	/**
	 Metodo que Borra un Usuario de la base de datos mediente la id

	 * @param id
	 */
	public void delete(int id) {
		
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE from usuarios WHERE id= ?");
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
	 * metodo que inserta un usuario en la base de datos
	 * @param user
	 */
	public void insertar (Usuario user)  {
		

		
		
		
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(" INSERT into usuarios (name, apellidos, correo, pass)"
						
						+ " VALUE(?, ?, ?, ?) ");
			
		
			ps.setString(1, user.getNombre() );
			ps.setString(2, user.getApellidos());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPass());
			
			
			
			int insertado = ps.executeUpdate();
			
			if (insertado == 1) {
				
				System.out.println("inserion en bbdd correcta");
			
			}else {
				
				System.out.println("Error insercion en bbdd incorrecta ");

			}
			ps.close();
			
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al insertar en la BBDD");
				e.printStackTrace();
			}
			
			
			
			
			
		
	}
	
	/**
	 * metodo que retorna todos los usuarios de la bd en un list 
	 * @return
	 */
	public ArrayList<Usuario> listarUsuarios(){
		ArrayList <Usuario >usuarios = null;
				
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement("SELECT * FROM usuarios");
			
		ResultSet rs = ps.executeQuery();
		
		
		
		if (rs.next()) {
			
		
		while(rs.next()) {
			if(usuarios == null) {
				
				usuarios = new ArrayList<>();
				}
			usuarios.add(new Usuario(rs.getInt("id"),rs.getString("name"),rs.getString("apellidos")
					,rs.getString("correo"),rs.getString("pass")));
		
			
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
		
	
		
	
		return  usuarios;
	}
	
	/**
	 * metodo que retorna un usuario de la BD mediante su busqueda por ID

	 * @param id
	 * @return
	 */
	public Usuario findbyID(int id) {
		
		Usuario user = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT* FROM usuarios WHERE id= ? ");
			
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				user = new Usuario(rs.getInt("id"),rs.getString("name"),rs.getString("apellidos")
						,rs.getString("correo"),rs.getString("pass"));
			}else {
				
				System.out.println("No existe ningun usuario con esa ID");
			}

			ps.close();
			rs.close();
			
				
				
		} catch (SQLException e) {
			
			System.out.println("Error al buscar por id");
			
		}
		
		
		return user ; 
		
		
	}
/**
 * metodo que retorna un usuario de la BD mediante su busqueda por email

 * @param correo
 * @return
 */
public Usuario findbyMail(String correo) {
		
		Usuario user = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT* FROM usuarios WHERE correo = ? ");
			
			ps.setString(1, correo);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				user = new Usuario(rs.getInt("id"),rs.getString("name"),rs.getString("apellidos")
						,rs.getString("correo"),rs.getString("pass"));
			}else {
				
				System.out.println("no existe ningun usuariocon el ese correo");
			}

			ps.close();
			rs.close();
			
				
				
		} catch (SQLException e) {
			
			System.out.println("Error al buscar por Correo");
			
		}
		
		
		return user ; 
		
		
	

}
/**
 * metodo que actualiza el password de un usuario ,necesita del login antiguo y el 
 * nuevo password
 * @param login
 * @param pass
 * @throws SQLException
 */
public void  updatePassword(String login , String pass) throws SQLException {
	 
	  
	  PreparedStatement ps = conn.prepareStatement("UPDATE usuarios SET password = ? WHERE login = ?");
			  
	  ps.setString(1, pass);
	  ps.setString(2, login);
	  
	  int insert = ps.executeUpdate();
	  
	  if(insert ==1) {
		  
		  System.out.println("Registro modificado correctamente");
	  }
	  
	  ps.close();
	  
}


}