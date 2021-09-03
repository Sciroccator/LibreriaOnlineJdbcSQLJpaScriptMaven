package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;


import java.io.IOException;

import java.io.StringWriter;
import java.io.Writer;

import dao.DaoLibro;

public class Libro implements Jsonable {
	

	/**
	 * atributos de Libro
	 * @param id
	 * @param nombre
	 * @param isnb
	 * @param precio
	 * @param autor
	 */
	
	private int id ;
	private String nombre;
	private String isnb;
	private Double precio;
	private String autor;

	/**
	 * constructor Vacio
	 */
	
	public Libro() {
		
	}
	
	/**
	 * constructor completo 
	 * @param id
	 * @param nombre
	 * @param isnb
	 * @param precio
	 * @param autor
	 */
	public Libro(int id, String nombre, String isnb, Double precio, String autor) {
		
		this.id =id;
		this.nombre = nombre;
		this.isnb = isnb;
		this.precio = precio;
		this.autor = autor;
	}
    /**
     * Constructor sin ID
     * @param nombre
     * @param isnb
     * @param precio
     * @param autor
     */
    public Libro( String nombre, String isnb, Double precio, String autor) {
		
		
		this.nombre = nombre;
		this.isnb = isnb;
		this.precio = precio;
		this.autor = autor;
	}
	
    /**
	 * Metodo para insertar un Libro en BD conecta con la clase Dao
	 */
	
	
	public void guardar() {
		
		try {
			DaoLibro.getInstance().insert(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 /**
		 * Metodo para borrar un Libro en BD conecta con la clase Dao
		 * necesita de un parametro id
		 */
	public static void borrar(int id) {
		
		try {
			DaoLibro.getInstance().delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para buscar un Libro con un isnb concreto en BD conecta con la clase Dao
	 * necesita de un parametro String "isnb"
	 */
	public Libro buscarIsnb(String isnb) {
		Libro l= null;
		try {
			l = DaoLibro.getInstance().findIsnb(isnb);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return l;
				
	}
	/**
	 * Metodo para buscar un Libro con un titulo concreto en BD conecta con la clase Dao
	 * necesita de un parametro String "nombre"
	 */
	
	public Libro buscarNombre(String nombre) {
		Libro l= null;
		try {
			l = DaoLibro.getInstance().findNombre(nombre);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return l;
				
	}
	/**
	 * Metodo para buscar un Libro en BD por el id  conecta con la clase Dao
	 * necesita de un parametro int "id"
	 */
	public static Libro buscarId(int id) {
		Libro l= null;
		try {
			l = DaoLibro.getInstance().findId(id);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return l;
				
	}
	
	/**
	 * Metodo para devolver todos los Libros de la base de datos 
	 * retorna un List con todos los libros de la base de datos
	 * @return libros
	 */
	public static ArrayList<Libro> listarLibros(){
		ArrayList<Libro>libros= null;
		
		
		try {
			libros = DaoLibro.getInstance().ListLibros();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return libros;
		
		
	}
	/**
	 * Metodo para actualizar el precio de  un Libro en BD conecta con la clase Dao
	 * necesita de un parametro  int id y de otro double precio
	 */
	
	public static void actualizarPrecio(int id , double precio) {
		
		try {
			DaoLibro.getInstance().updatePrecio(id, precio);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * Getters and Setters
	 */
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIsnb() {
		return isnb;
	}

	public void setIsnb(String isnb) {
		this.isnb = isnb;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Sobre escribimos el metodo toJson() de Jsonable para poder serializar
	 * un Objeto Libro y convertirlo a Json
	 */
	
	@Override
	public String toJson() {
		   final StringWriter writable = new StringWriter();
	        try {
	            this.toJson(writable);
	        } catch (final IOException e) {
	        }
	        return writable.toString();
		
	}

	@Override
	public void toJson(Writer writer) throws IOException {
		  final JsonObject json = new JsonObject();
		    json.put("id", this.getId());
	        json.put("nombre", this.getNombre());
	        json.put("isnb", this.getIsnb());
	        json.put("precio", this.getPrecio());
	        json.put("autor", this.getAutor());
	        
	        json.toJson(writer);
		
		
		
	}
	
	
	

}
