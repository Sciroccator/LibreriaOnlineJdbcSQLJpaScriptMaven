package controlador;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.github.cliftonlabs.json_simple.Jsoner;




import modelo.Libro;



/**
 * Servlet implementation class ServletControlador
 */
/**
 *
 */
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * Constructor vacio
	 */
    public ServletControlador() {
      
    }
    
    
    /**
     * Metodo que distribuye el trafico de peticiones en funcion de la opcion
     * que se ha marcado en la vista mediante el parametro "opcion"
     * @param request
     * @param response
     */
    protected void controler(HttpServletRequest request, HttpServletResponse response) {
    	

    	/**
    	 * Switch para filtrar la opcion marcada en la vista, en funcion de esta 
    	 * opcion deriva las peticiones a un metodo u otro
    	 */
    	String opcion = request.getParameter("opcion");
    	switch(opcion) {
    	
    	case "1":
    		
    	
			try {
				alta(request , response);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    	  
    	  break;
    	
    	case "2":
    		
        	
			try {
				baja(request , response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
      	  
      	  break;
    	case "3":
    		
        	
			try {
				consultar(request , response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      	  
      	  break;
      	  
    	case "4":
    		
        	
      	  modificarPrecio(request , response);
      	  
      	  
      	  
      	  
      	  
      	  break;
      	  
    	case"5":
    		
    		
    		try {
				buscarNombre(request ,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		break;
      	
    	}
    	
    	
    	
    }
    /**
     * Metodo para dar de alta un Libro en la BD
     * @param request
     * @param response
     * @throws IOException
     */
    protected void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	// se recogem los valores que formaran los atributos del Libro
    	String nombre = request.getParameter("nombre");
    	String  isnb = request.getParameter("isnb");
    	Double precio = Double.parseDouble(request.getParameter("precio"));
    	String autor =request.getParameter("autor");
    	// se genera un Libro con dichos atributos
    	Libro l = new Libro(nombre,isnb,precio,autor);
    	// se guarda dicho libro;
    	l.guardar();
    	// se redireciona la response a ListarLibros.jsp
    	response.sendRedirect("listarLibros.jsp");
    	
    	
    	
    	
    }
    /**
     * Metodo para dar de baja un libro en la BD
     * @param request
     * @param response
     * @throws IOException
     */
    
    protected void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	
    	// se recoge la id de el libro
    	int id = Integer.parseInt(request.getParameter("identy"));
    	// se llama al metodo borrar y se le envia la id
    	Libro.borrar(id);
    	// se redirecciona a listarLibros.jsp
    	response.sendRedirect("listarLibros.jsp");

    	
    	
    	
    	
    	
    }
 /**
 * metodo para enviar un libro requerido por isnb a la vista
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
protected void consultar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	   
	   try (PrintWriter out = response.getWriter()) { 
		// se recoge el isnb del request
    	String isnb = request.getParameter("isnb");
    	
    	// se guarda la busqueda por isnb en  "l"
    	Libro l = new Libro().buscarIsnb(isnb);
    	// se serializa a json el libro encontrado "l"
    	String jsonLibro = Jsoner.serialize(l);
    	// impresion en consola para controlar que es correcto el json
    	  	System.out.println(jsonLibro);
    	  	// se añade el json al PrintWriter del response 
    	out.println(jsonLibro);
		
	   }
    	
    }
    /**
     * 
     * metodo para modificar el precio del libro 
     * @param request
     * @param response
     */
    protected void modificarPrecio(HttpServletRequest request, HttpServletResponse response) {
	
    	// recoge el id del request 
    	int id = Integer.parseInt(request.getParameter("id"));
    	
    	// recoge el precio del request
    	double precio= Double.parseDouble(request.getParameter("precio"));
    	// modifica el precio en BD
    	Libro.actualizarPrecio(id, precio);
    	
    	
  	   
	
	
    }
    
    /**
     * Metodo para buscar un libro por el Titulo
     * @param request
     * @param response
     * @throws IOException
     */
    protected void buscarNombre(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
 	   
 	   try (PrintWriter out = response.getWriter()) { 
 		// se recoge el titulo del libro
     	String nombre = request.getParameter("nombre");
     	// se recoge el objeto libro buscado con el metodo buscarnombre()
     	Libro l = new Libro().buscarNombre(nombre);
     	// se serializa a json el Libro "l"
     	String jsonLibro = Jsoner.serialize(l);
     	// impresion en consola del json para ver si es correcto
     	  	System.out.println(jsonLibro);
     	// se añade el json al response mediante el printWriter
     	out.println(jsonLibro);
 		
 	   }
    	
    	
    	
    	
    }
       
    
	/**
	 * desvia el trafico de peticiones a controler()
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		controler(request,response);
		
		
	}

	/**
	 * desvia el trafico de peticiones post a get 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
