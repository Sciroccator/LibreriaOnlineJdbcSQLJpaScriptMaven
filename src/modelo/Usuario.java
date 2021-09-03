package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoUsuario;

public class Usuario {
	
	/**
	 * @param id
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param pass
	 */
	
	private int userID;
	private String nombre;
	private String apellidos;
	private String email;
	private String pass;
	
	
	/**
	 * Constructor Vacio
	 */
	
	public Usuario() {
		
	}
	



	/**
	 * constructor sin ID
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param pass
	 */
	public Usuario(String nombre, String apellidos, String email, String pass) {
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.pass = pass;
	}




	/**
	 * Constructor completo
	 * @param id
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param pass
	 */
	public Usuario(int id ,String nombre, String apellidos, String email,String pass) {
		
		this.userID = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.pass= pass;
	}
	
	
	/**
	 * Metodo para insertar un usario en la BD conecta con la clase dao
	 */
	public void insertarUsuario() {
		
		
		DaoUsuario.getInstance().insertar(this);
	}

	
	/**
	 * Metodo para devover lista de Usuarios retorna un List de usuarios
	 * 
	 * @return
	 */
	public static ArrayList<Usuario> ListarUsuarios(){
		
		return DaoUsuario.getInstance().listarUsuarios();
		
	}
	
	/**
	 * Metodo para Buscar un Usuario por ID retorna un objeto Usuario 
	 * @param id
	 * @return
	 */
	public static Usuario encontrarID(int id) {
		
	return	DaoUsuario.getInstance().findbyID(id) ;
	
	}
	
	/**
	 * Metodo para buscar un Usuario por el Email retorna un Objeto usuario
	 * @param correo
	 * @return
	 */
	public static Usuario EncontrarCorreo (String correo) {
		
		return DaoUsuario.getInstance().findbyMail(correo);
		
	}
	/**
	 * Metodo para borrar un usario de la BD necesita de un ID para eliminarlo
	 * @param id
	 */
	public void borrarUsuario(int id) {
		DaoUsuario.getInstance().delete(id);
		
	}
	/**
	 * metodoa para modificar el password de un Usuario necesita del login y el nuevo
	 * password
	 * @param login
	 * @param pass
	 */
	public void ModificarUsuario(String login, String pass) {
		try {
			DaoUsuario.getInstance().updatePassword(login,pass );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	


	/**
	 * Getters y Setters
	 */

	public int getUserID() {
		return userID;
	}



	public void setUserID(int userID) {
		this.userID = userID;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public String getEmail() {
		return email;
	}



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	
	
	

}
