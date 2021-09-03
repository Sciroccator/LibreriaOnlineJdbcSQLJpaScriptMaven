package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoLibrero;

/**
 * Clase para el objeto Librero 
	 
 */

public class Librero {
	
	/**
	 * Atributos de Librero
	 * @param Id
	 * @param login
	 * @param pasword
	 */
	
	
	
	private int id ;
	private String login;
	private String pasword;
	/**
	 * constructor vacio
	 
	 */
	
	public Librero() {
		
		
	}
	/**
	 * constructor sin Id
	 * @param login
	 * @param pasword
	 */
	public Librero(String login, String pasword) {
	
	
		this.login = login;
		this.pasword = pasword;
		
	}

	/**
	 * constructor completo
	 * @param id
	 * @param login
	 * @param pasword
	 */
	public Librero(int id, String login, String pasword) {

		this.id = id;
		this.login = login;
		this.pasword = pasword;
		
	}
	
	/**
	 * metodo insertar nuevo librero BD
	 
	 */
	
    public void InsertarLibrero() {
    	
    	try {
			DaoLibrero.getInstance().insert(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    /**
	 * metodo borrar librero de BD
 	 
	 */
    
    public void BorrarLibrero() {
    	try {
			dao.DaoLibrero.getInstance().delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }
    /**
	 * metodo Modificar librero en BD
 	 
	 */
    
    public void ModificarLibrero() {
    	
    	try {
			DaoLibrero.getInstance().updatePassword(id, login, pasword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
	 * metodo buscar librero  por el Login en BD
 	 
	 */
    public Librero BuscarLibrero(String login) throws SQLException {
    	
    	Librero l =DaoLibrero.getInstance().findLogin(login);
    	    	
    	
    	return l;
    }
    
    /**
     * Metodo para retornar una lista de libreros de la BD
     * @return
     * @throws SQLException 
     */
    public static ArrayList<Librero> ListarLibreros() throws SQLException{
    	Librero l= new Librero();
    	
    	ArrayList<Librero> libreros;
	
			libreros = DaoLibrero.getInstance().listLibreros();
	
			
    	
    	
    	
    	return libreros;
    	
    	
    }
    
    /**
	 * Getters and Setters de los Atributos
 	 
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	

}
