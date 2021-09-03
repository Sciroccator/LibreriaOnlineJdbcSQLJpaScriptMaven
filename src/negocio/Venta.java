package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

public class Venta {
	
	/**
	 * @param importe
	 * @param usuario
	 * @param titulo
	 * @param autor
	 */
	
	private int id;
	private double importe;
	private String usuario;
	private String titulo;
	private String autor ;
	
	/**
	 * Constructor vacio
	 */
	public Venta() {
		
		
	}

	/**
	 * constructor sin id
	 * @param importe
	 * @param usuario
	 * @param titulo
	 * @param autor
	 */
	public Venta(double importe, String usuario, String titulo, String autor) {
		super();
		this.importe = importe;
		this.usuario = usuario;
		this.titulo = titulo;
		this.autor = autor;
	}

	/**
	 * constructor completo
	 * @param id
	 * @param importe
	 * @param usuario
	 * @param titulo
	 * @param autor
	 */
	public Venta(int id, double importe, String usuario, String titulo, String autor) {
		super();
		this.id = id;
		this.importe = importe;
		this.usuario = usuario;
		this.titulo = titulo;
		this.autor = autor;
	}
	
	/**
	 * metodo para insertar una venta 
	 * @param usuario
	 */
	public static void insertarVentas(String usuario) {
		// contacta con la clase dao y le pasa el Usuario a el metodo insert()
		try {
			daoVenta.getInstance().insert(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * Metodo para listar las ventas de un Usuario
	 * @param usuario
	 * @return
	 */
	public ArrayList<Venta> ListarVentas(String usuario)  {
		
		
	// se retorna las ventas en un list que nos manda la clase dao al pasarle el usuario 
    // al metodo findUsuario
		
			ArrayList<Venta> ventas = null;
			try {
				ventas = daoVenta.getInstance().findUsuario(usuario);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return ventas ;
		
		
		
		
	}
	/**
	 * metodo para retornar el importe de las ventas de un usuario
	 * @param Usuario
	 * @return
	 */
	public double importeVenta(String Usuario) {
		// variable para recoger el importe
		double total= 0;
		//nuestra clase dao nos reporta el importe de las ventas mediante el metodo
		// totalImporte() al cual le pasamos el usuario 
		try {
			total = daoVenta.getInstance().totalImporte(Usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// retornamos el importe 
		return total;
		
		
		
		
	}
	
	
	    // Setters y Getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	

}
