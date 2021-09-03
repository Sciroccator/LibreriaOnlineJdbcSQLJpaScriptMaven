<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "negocio.Carrito" %>
 <%@page import="java.util.ArrayList" %> 
  <%@ page import = "modelo.Libro" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
// funcion on load que se ejecuta al inicio
window.onload=function() {
	
	  // se recoge el valor 'useri'  en la variable 'dato'
	var queryString = window.location.search;
    var urlParams = new URLSearchParams(queryString);
    var dato = urlParams.get('useri');
	// se entrega 'dato' al input con id 'user'
	
	document.getElementById('user').value = dato;
	
	
}


</script>
</head>
<body>

<input type= "text" name ="usuario" id ="user" value ="">



<%   
// se inica la variable contador a 0 y se guardan los libros que tiene 
// el usuario en la tabla carrito de la BD en un List
int contador =0;
String usuario= request.getParameter("useri"); 
Carrito carro = new Carrito();
ArrayList<Libro> libros= carro.FindByUsuario(usuario);

%>
<!-- se genera una tabla para mostrar los libros pendientes en el carrito de compras  -->
<label> Libros pendientes en Carrito de compras </label>

<table>
<tr>
<th>ID</th><th>TITULO</th><th>AUTOR</th><th>PRECIO</th>

</tr>
<%  
// si el List de libros no es nulo procedemos a iterarlo e ir llenando los TD de la tabla 
// con los datos de cada libro en cada iteracion
if (libros != null){
	
	for (Libro li : libros){%>
	
	<tr>
	<td id="identy"><%=li.getId() %></td>
	
	<td><%=li.getNombre() %></td>
	<td><%=li.getAutor() %></td>
	<td><%=li.getPrecio() %></td>
	<!-- aumentamos el contador con cada libro para saber las unidades que tenemos en el carrito -->
	<% contador++; %>
<!-- a cada iteracion se le añade un boton para poder borar el libro enlazando con el controlador
 Servlettienda y mandandole la opcion 3 el nombre del usuario y la id del libro -->
	<td><a href="ServletTienda?opcion=3&usuario=<%=usuario %>&id=<%=li.getId() %>"><input type="button" value="Eliminar Libro"></a></td>
	
	</tr>
<% } %>
		

<tr>
<td> UDS  </td><td>  </td><td> TOTAL </td><td>PVP </td>
</tr>
<tr>
<td>**  </td><td> ** </td><td> ** </td><td>** </td>
</tr>
<% 
// recogemos el importe de los libros que tiene el usuario en el carrito
double importe = carro.importe(usuario) ;
%>
<tr>
<!-- mostramos unidades e importe de la compra -->
<td><%=contador %>  </td><td>  </td><td>  </td><td><%=importe %> </td>


</tr>

</table>
<%
// si no hay libros en el list le enviamos un aviso y no pintamos la tabla 
} else{%>

<h3>Aun no tienes ningun Libro en el Carrito</h3>


<%} %>
<!-- formulario para realizar el envio de la opcion al cotrolador y el usaurio que 
quiere pagar los libros de su carrito  -->
<form action="ServletTienda" name="compraFinal" method="GET">

<input type="hidden" class="usuarios" name = "usuarioCompra" value= <%=usuario %>>
<input type="hidden" name = "opcion" value="4">

<input type="submit" name = "enviarCompra" value="PAGAR COMPRA">
   
  
</form>

<!-- si no queremos pagar le damos la opcion al usuario de seguir comprando  
redireccionando a la lista de libros y arrastrando su usuario -->
<a href="listarTienda.jsp?usuario=<%=usuario %>"><input type="button" value="Seguir comprando"></a>







</body>
</html>