package controlador;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import modelo.Librero;
import modelo.Usuario;


/**
 * Servlet implementation class GestionLogin
 */


@WebServlet("/GestionLogin")
public class GestionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 *constructor vacio
	 */
	
    public GestionLogin() {
       
    }
    /**
    *Metodo que recoge el request y response de la vista y que en funcion de la opcion 
    *marcada en el request envia dicho request y response al metodo adecuado
    */
    
    protected void gestionLogin(HttpServletRequest request, HttpServletResponse response) {
    	
    	/**
    	 * Switch para filtrar la opcion marcada en el request
    	 */
    	
        switch(request.getParameter("opcion")) {
    	
    	case "1":
    		try {
				gestionLogarse(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		break;
    	
    	
    	case "2":
    		
    		
    		try {
				gestionALtaLogin(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    		
    		break;
    	case"4":
    		
    		try {
				gestionALtaLoginLibrero(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		break;
    	}
    
    		
    		
    
    }
    	   
    	
    	
    
    /**
     * Metodo que sirve para recoger las cookies
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void gestionLogarse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// direccion para redireccionar el response
    	String direccion = "loginUsuario.jsp";
    	
    	// se recogen las cookies la vista y se busca la que corresponde 
    	//con el "email" y luego se añade ese dato al request con el nombre 
    	// de "email"
    	Cookie cookies[] = request.getCookies();
		for(Cookie c :cookies) {
			
		String e=	c.getName();
		if(e.equals("email")) {
			
			request.setAttribute("email", c.getValue());
			
		}
		
		}
       
    	
    	// se le marca la direccion de entrega al requestDispatcher
		// y se lanzan el request y el response
    	RequestDispatcher rd = request.getRequestDispatcher(direccion);
    	rd.forward(request, response);
    
    	
    }

/**
 * Metodo para conprobar el Usuario y login son correctos 
 *
 */
		
		protected void gestionALtaLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			// direccion a la que se lanzara el response y el request
			String direccion = "loginUsuario.jsp";
			
			//Mensaje que va se lanza en el Request cambia en funcion de la necesidad
			
	    	String mensaje ="";
			
	    	// recoge el password de la vista
			String password = request.getParameter("password");
			password = password.trim();
			// recoge el email de la vista
    		String email = request.getParameter("email");
    		email = email.trim();
    		// se comprueba que el Usuario existe en la BD
    		Usuario user = new Usuario().EncontrarCorreo(email); 
    		// if para determinar que hacer si existe o no el usario
    		if (user == null) {
    			//si es null se manda a la vista el mensaje de que no existe el usuario 
    			mensaje = "El correo no existe en la Base de datos , Ha de Registrarse antes  ";
    			request.setAttribute("mensaje", mensaje);
    			RequestDispatcher rd = request.getRequestDispatcher(direccion);
		    	rd.forward(request, response);
	        	
				
    			
    		}else{// si el usuario existe , se comprueba que el password es correcto 
    			//con otro if
    	          if (password.equals(user.getPass())) {
    	        	  
    	        	  // si el password es correcto guadamos el email del usuario
    	        	 // en la variable "usuario" para añadirla al response posteriormente
    	        	String usuario =user.getEmail();  
    				// creamos una cookie con el email del usario
    				Cookie mycookie= new Cookie("email",user.getEmail());
    				// determinamos el tiempo de vida de la cookie
    				mycookie.setMaxAge(60*10);
    				// y aññadimos la cookie al response
    				response.addCookie(mycookie);
    				// redireccionamos a ListarTienda.jsp y le añadimos el valor usuario
    				response.sendRedirect("listarTienda.jsp?usuario="+usuario);
    				
    				
    			}else {
    				// si el password es errone simplemente añadimos un 
    				//mensaje avisando y hacemos un forward
    				mensaje = "El password es erroneo intentelo de nuevo";
    				request.setAttribute("mensaje", mensaje);
    	        	
    		
    		    	RequestDispatcher rd = request.getRequestDispatcher(direccion);
    		    	rd.forward(request, response);
    	        	
    	        	
    				
    			}
    			
    		}
    	
    		
			
			
			
		}
		
		/**
		 * metodo para determinar si el login y password del librero son correctos
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws SQLException
		 */
		protected void gestionALtaLoginLibrero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			// direccion para redireccionar el request y response
			String direccion = "loginLibrero.jsp";
			// mensaje segun necesidad
	    	String mensaje ="";
			
	    	// recogida de password de la vista
			String password = request.getParameter("password");
			password =password.trim();
			// recogida de login de la vista
    		String login = request.getParameter("login");
    		login = login.trim();
    		//buscamos si el Librero existe con ese login
    		Librero l = new Librero().BuscarLibrero(login);
    				
    		if (l == null) {// si no existe  se manda un mensaje a la vista de
    			// aviso 
    			mensaje = "No hay ningun librero Authorizado con ese Login ";
    			request.setAttribute("mensaje", mensaje);
    			RequestDispatcher rd = request.getRequestDispatcher(direccion);
		    	rd.forward(request, response);
	        	
				
    			
    		}else{// si existe se pasa ha conprobar el password
    	          if (password.equals(l.getPasword())){
    	        	 
    				// creamos una cookie que tendra una llave"login " y un valor
    	        	//del login del librero
    				Cookie mycookie= new Cookie("login",l.getLogin());
    				// determinamos la vida de la cookie
    				mycookie.setMaxAge(60*10);
    				// añadimos la cookie a la response y redireccionamos a IndexLibrero.jsp
    				response.addCookie(mycookie);
    				response.sendRedirect("indexLibrero.jsp");
    				
    				
    			}else {
    				
    				// si el password es erroneo mandamos un mensaje de aviso a la vista 
    				mensaje = "El password es erroneo intentelo de nuevo";
    				request.setAttribute("mensaje", mensaje);
    	        	
    		
    		    	RequestDispatcher rd = request.getRequestDispatcher(direccion);
    		    	rd.forward(request, response);
    	        	
    	        	
    				
    			}
    			
    		}
    	
    		
			
			
			
			
		}
    

	/**
	 * Redireccionamos le request y response de la vista al metodo gestor
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		gestionLogin(request,response);
	}

	/**
	 * redirecciona el trafico de Post a Get
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
