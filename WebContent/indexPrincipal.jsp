
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import = "dao.DaoLibro" %>   
 <%@ page import = "modelo.Libro" %>
 <%@page import="java.util.List" %>
 <%@page import="java.util.ArrayList" %>  

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script type="text/javascript">
    
    function aviso(){
    	
    alert('Necesita estar Logado para comprar Libros');
    alert('Si no tiene cuenta de usuario ha de darse de ALta en la Web');
    	
    	
    	
    }
    
    
    </script>
</head>

<body>

<!-- indice principal de la aplicacion  -->

    <h1>Tienda Libros Quevedo</h1>
   
<label> Usuario </label>

<input type="text" class="usuarios" name = "usuario2" value="" readonly>


    
<%
// recogemos la lista de libros de la BD y lo guardamos en un list 
 ArrayList<Libro>libros= Libro.listarLibros();
%>

	<!-- pintamos una tabla para los Libros de la BD -->

<%
// si el list no es nulo pintamos la tabla
if (libros != null){%>
<table border= 1px>
<tr style="background-color: aqua;"> <th>ID</th><th>TITULO</th> <th>ISNB</th> <th>AUTOR</th> <th>PRECIO</th>  </tr>

<% 
// iteramos el list y rellenamos la tabla
for(Libro l : libros){%>
<tr>
<td><%=l.getId() %></td>
<td><%=l.getNombre() %></td>
<td><%=l.getIsnb() %></td>
<td><%=l.getAutor() %></td>
<td><%=l.getPrecio() %></td>
<td>

<!-- Los botones no trabajan hasta que no haga login el usuario 
al hacer click se le deriva a una funcion JS que le avisa de ello-->
 <a href="javascript:aviso()"><input type="button" value="al Carrito"></a> 

   
 
</td>
<td>

<a href="javascript:aviso()"><input type="button" value="Comprar ya"></a> 
</td>



</tr>

<%}
%>
</table>
<%}else  {// si no hay libros en la base de dados mandamos un aviso %>
<h3> No hay libros en la Base de datos para vender </h3>

<% }%>

<br>
<a href="javascript:aviso()"><input type="button" value="Al Carrito"></a> 

<br>    
    
    
    
<br>
    
    
    

<!--  nos envia al menu de libreros -->
    <label>
        Entrada para Librero 
        </label>
             
           <a href="loginLibrero.jsp" >   
           <input type= "button" name ="librero" value= "Acceso Libreros" id="accesoLibrero" >
           </a>
    <label>
<!--  nos envia al menu de Usuarios de la web -->
        Entrada para Usuarios 
         </label>
                 
            <a href="indexUsuario.jsp" >   
            <input type= "button" name ="Usuario" value= "Acceso Usuarios" id="AccesoUsuario" >
            </a>

    
</body>
</html>