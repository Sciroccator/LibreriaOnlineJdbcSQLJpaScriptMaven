<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import = "dao.DaoLibro" %>   
 <%@ page import = "modelo.Libro" %>
 <%@page import="java.util.List" %>
 <%@page import="java.util.ArrayList" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>



<script type="text/javascript">
// funcion on load para el inicio de la pagina 
window.onload=function() {
	
	  // recoegemos el valor de 'usuario' de la url y lo guardamos en 'dato'
	var queryString = window.location.search;
    var urlParams = new URLSearchParams(queryString);
    var dato = urlParams.get('usuario');
	
	
// recogemos todos los elementos html de la clase "usuarios" en la variable 'users'
  var users = document.getElementsByClassName("usuarios");

  // iteramos esa variable y a cada elemento le cambiamos el valor por el valor de 'dato'
 var i;
 for (i = 0; i < users.length; i++) {
  users[i].value = dato;
 }

}





</script>
</head>
<body>

<!--  boton para volver al menu de inicio -->
<a href="indexUsuario.jsp" ><input type="button" value="Menu Inicio"> </a>
<br>
<br>

<label> Usuario </label>

<input type="text" class="usuarios" name = "usuario2" value="">


    
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
<!-- por cada libro se añade un boton de mandarlo al carrito  y otro de compra inmediata -->
<!-- formulario que enviuia al controlador el usuario la id del libro y la opcion del controlador -->
<form action="ServletTienda" name="carro" method="GET">

<input type="hidden" class="usuarios" name = "usuario" value="">
<input type="hidden" name = "opcion" value="1">
<input type="hidden" name = "id" value="<%=l.getId() %>">
<input type="submit" name = "enviar" value="al Carrito">
   
  
</form>
</td>
<td>
<!-- formulario que envia al controlador el usuario el id del libro y la opcion del controlador  -->
<form action="ServletTienda" name="compra" method="GET">

<input type="hidden" class="usuarios" name = "usuarioCompra" value="">
<input type="hidden" name = "opcion" value="2">
<input type="hidden" name = "idCompra" value="<%=l.getId() %>">
<input type="submit" name = "enviarCompra" value="Comprar Ya">
   
  
</form>
</td>



</tr>

<%}
%>
</table>
<%}else  {// si no hay libros en la base de dados mandamos un aviso %>
<h3> No hay libros en la Base de datos para vender </h3>

<% }%>

<!-- formulario boton para ir al carrito enviandole el usuario como input oculto -->
<form action="carrito.jsp" name="carro2" method="GET">

<input type="hidden" class="usuarios" name = "useri" value="">

<input type="submit" name = "enviar" value="al Carrito">
</form>

</body>
</html>