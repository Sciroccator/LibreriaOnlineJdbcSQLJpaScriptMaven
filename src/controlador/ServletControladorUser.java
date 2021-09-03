package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.DaoUsuario;
import modelo.Librero;
import modelo.Usuario;

@WebServlet("/ServletControladorUser")
public class ServletControladorUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	// constructor vacio
    public ServletControladorUser() {
       
        
        
     
    }


    
    
    /**
     * metodo que controla el alta de usuarios  el listado de usuarios en BD
     * y el listado de los Libreros
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    protected void respuestas(HttpServletRequest request,HttpServletResponse response) 
    		throws IOException, ServletException, SQLException {
    	
    	response.setContentType("text/html;charset=UTF-8");
    	
            // creacion del printWriter	
			PrintWriter pw = response.getWriter();
			// mensaje modificable en funcion de la necesidad
			String mensaje = "ha habido un problema en la respuesta del servidor ";
			// re recoge la opcion enviada desde la vista 
			String opcion = request.getParameter("opcion");
			
			
			// if para el control de acciones en funcion de la opcion marcada 
			if (opcion.equals("1")) // opcion 1
				
			    {
				// se recogen los parametros del usuario que nos llegan del 
				// cuestionario se alta usuario 
				String nombre = request.getParameter("nombre");
				String apellidos = request.getParameter("apellidos");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				
				//se busca si el email de ese usuario ya existe en la BD
				Usuario user = new Usuario().EncontrarCorreo(email);
				
				// If para controlar si el email existe en BD o no
				
			   if (user != null) // si el email existe no se puede dar de alta 
				   // enviamos un mensaje a la vista
			   {
		        mensaje = " El Usuario no puede darse de alta, existe un usuario con el mismo email ";
			
			   }else {// si no existe el email en BD podemos proceder al alta 
				   // del usuario 
				
				 // se inserta el usuario en la BD  
				Usuario user2 = new Usuario(nombre,apellidos,email,password);
				user2.insertarUsuario();
				// se modifica el mensaje para la vista 
				mensaje = "El usuario ha  sido dado de alta correctamente";
			    // se procede a recoger la Id de ese nuevo usuario y su nombre 
				// y se añaden al request que volvera a la vista
				user2 = Usuario.EncontrarCorreo(email);
				request.setAttribute("id",user2.getUserID() );
				request.setAttribute("nombre", nombre);
				
			   }
				
			   // se añade el mensaje que corresponda en funcio de las necesidades 
			   // a el request , se le marca la direccion de la vista hacia donde 
			   // ira y se lanza el reques y response
			   request.setAttribute("mensaje", mensaje);
				RequestDispatcher rs = request.getRequestDispatcher("/mostrarUsuario.jsp");
			   rs.forward(request, response);
			   
				
			}else if(opcion.equals("2")) 
			    // si la opcion marcada por la vista es "2 " entra al if para 
				// retornarnos una lista de usuarios a la viasta
			    {
				
				// guardamos la Lista de Usuarios de la BD en un List 
				ArrayList<Usuario> users = Usuario.ListarUsuarios(); 
				// añadimos la lista al request
				request.setAttribute("listaUsuarios", users);
				// marcamos la direccion de la vista que va a recibir el list
				RequestDispatcher rd = request.getRequestDispatcher("/listaUsuarios.jsp");
				// lanzamos el Request y el response
				rd.forward(request, response);
				
				
				
				
				
				
			}else if(opcion.equals("3")) 
				//si la opcion marcada en la vista es "3" entra en el if
				// para retornarnos u list de Libreros de la BD
			     {
				
				// se recoge el List de libreros y se añade al Request
				ArrayList<Librero> libreros = Librero.ListarLibreros();
				request.setAttribute("listaLibreros", libreros);
				// se envia el list a la Vista
				RequestDispatcher rd = request.getRequestDispatcher("/listarLibreros.jsp");
				
				rd.forward(request, response);
				
				
				
				
				
				
			}
		
    	
    	
    	
    	
			
    	
    }

	/**
	 * se desvia el trafic de peticiones a el metodo respuestas()
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			respuestas(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * se desvia el trafico de post a get
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
