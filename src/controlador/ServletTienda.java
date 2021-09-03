package controlador;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import modelo.Libro;
import negocio.Carrito;
import negocio.Venta;
import negocio.daoCarrito;
import negocio.daoVenta;

/**
 * Servlet implementation class ServletTienda
 */
@WebServlet("/ServletTienda")
public class ServletTienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * constructor vacio
     */
    public ServletTienda() {
       
    	
    	
    	
    }
    
    
   
    /**
     * metodo para derivar el trafico de peticiones en funcion de 
     * la opcion enviada porla vista 
     * @param request
     * @param response
     */
    protected void controlador(HttpServletRequest request, HttpServletResponse response) {
    	
    	// switch para derivar las peticiones  enviadas por la vista a diversos
    	// metodos en funcion de la opcion marcada en la vista 
    	switch(request.getParameter("opcion")) {
    	
    	case "1":
    	
    	
    		try {
				guardarCarrito(request,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		
    		break;
    	case "2":
    		
    		try {
				pasarelaComprarLibro(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    		
    	case"3":
    		try {
				eliminarLibroCarrito(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    		
    	case"4":
    		
			try {
				pagoCompraFinal(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	  
    	    break;
    	    
    	case"5":
    		
    		 try {
				pagoCompraFinalDirecta(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	
    	}
    	
    	
    	
    	
    }

    
    /**
     * metodo parpasar libro de la tabla de libros a la tabla de carrito
     * @param request
     * @param response
     * @throws IOException
     */
    protected void guardarCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	// se crea el carrito , se recoge el id del libro y el usuario que realiza 
    	// la compra
		Carrito carrito = new Carrito();
		int id = Integer.parseInt(request.getParameter("id"));
		String usuario = request.getParameter("usuario");
		
		// se agrega el libro y el usuario al carrito
	    carrito.AgregarLibro(id, usuario);
	    // se redirecciona a listarTienda.jsp y se le envia el usuario
	    response.sendRedirect("listarTienda.jsp?usuario="+usuario);
    	
    	
    	
    	
    }
    
    
    /**
     * Metodo para reenviar un libro de la lista y el usuario que quiere comprarlo
     * a una nueva vista para proceder al pago 
     * @param request
     * @param response
     * @throws IOException
     */
    protected void pasarelaComprarLibro(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	// recoge el id del libro y el usuario del request y se los envia 
    	// a la vista Pasarela.jsp 
    	int id = Integer.parseInt(request.getParameter("idCompra"));
    	String usuario= request.getParameter("usuarioCompra");
    	response.sendRedirect("Pasarela.jsp?id="+id+"&usuario="+usuario);
    	
    	
    	
    	
    	
    	
    }
    
    /**
     * metodo que elimina un libro del carrito de un usario en concreto
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     */
    protected void eliminarLibroCarrito(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	// se recoge el usuario y el id del libro  del request que nos manda la vista
    	String usuario= request.getParameter("usuario");
    	int id = Integer.parseInt(request.getParameter("id"));
    	
    
    	// se crea el carrito
    	Carrito carrito = new Carrito();
    	
    	// se busca el libro en el carrito
    	Libro libro = Carrito.buscarId(id);
        // se elimina el libro del carito
    	carrito.eliminarLibro(id);
    
         // se redirecciona a ListarTienda y se envia el Usuario
        response.sendRedirect("listarTienda.jsp?&usuario="+usuario);
    	
    	
    	
    	
    	
    	
    	
    }
    
    /**
     * metodo para pasar los libros comprados por un usuario desde el Carrito de la tabla carrito
     * a la tabla ventas de la BD 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     */
    protected void pagoCompraFinal(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	
    	
    	// recogida de el usuario que realiza la compra
    	String usuario = request.getParameter("usuarioCompra");
    	
    	// insertamos la venta en BD
    	Venta.insertarVentas(usuario);
    	// eliminamos los libreos del usuario de el carrito
    	Carrito.eliminarVentaUsuario(usuario);
    	//redireccionamos a la vista y le enviamos el Usuario
    	response.sendRedirect("compra.jsp?usuario="+usuario);
    	
    	
    	
    	
    	
    }
 /**
  * Metodo para comprar un libro de manera directa desde la lista de libros
 * @param request
 * @param response
 * @throws SQLException
 * @throws IOException
 */
protected void pagoCompraFinalDirecta(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	
    	// recogemos el Id de l Libro del Request
    	int id = Integer.parseInt(request.getParameter("id"));
    	
    	// recogemos el Usuario del Request
    	String usuario = request.getParameter("usuarioCompra");
    	// agregamos el libro al carrito de manera temporal 
    	new Carrito().AgregarLibro(id, usuario);
    	// insertamos la venta en la tabla venta
    	Venta.insertarVentas(usuario);
    	// eliminamos los el libro o libros que tenga el usuario en el Carrito
    	Carrito.eliminarVentaUsuario(usuario);
         // redirecionamos a la vista compra.jsp y le enviamos el usuario
    	response.sendRedirect("compra.jsp?usuario="+usuario);
    	
    	
    	
    	
    	
    }
    
    
    
	/**
	 * Redireccionamos el trafico al metodo controlador()
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	controlador(request,response);
		
		
		
	}

	/**
	 * redirecionamos las peticiones de post al get
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
