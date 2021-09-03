<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "negocio.Venta" %>
 <%@page import="java.util.ArrayList" %> 
  <%@ page import = "modelo.Libro" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
// funcion on load al inicio de la pagina
window.onload=function() {
	//se recoge valor 'usuario' de la url en la variable 'dato'
	  
	var queryString = window.location.search;
    var urlParams = new URLSearchParams(queryString);
    var dato = urlParams.get('usuario');
	// se entrega el valor de 'dato' a el elemento con id 'user'
	document.getElementById('user').value = dato;
	
	
}


</script>
</head>
<body>

<input type= "text" name ="usuario" id ="user" value ="">



<%   
//se inica la variable contador a 0 y se guardan los libros que tiene 
//el usuario en la tabla carrito de la BD en un List
int contador =0;
String usuario= request.getParameter("usuario"); 
Venta venta = new Venta();
ArrayList<Venta> ventas= venta.ListarVentas(usuario);

%>
<!-- se genera una tabla para mostrar los libros que hemos comprado -->
<label> DETALLE DE LA COMPRA REALIZADA</label>

<table>
<tr>
<th>ID</th><th>TITULO</th><th>AUTOR</th><th>PRECIO</th>

</tr>
<%  
//si el List de libros no es nulo procedemos a iterarlo e ir llenando los TD de la tabla 
//con los datos de cada libro en cada iteracion
if ( ventas != null){
	
	for (Venta v : ventas){%>
	
	<tr>
	<td id="identy"><%=v.getId() %></td>
	
	<td><%=v.getTitulo() %></td>
	<td><%=v.getAutor() %></td>
	<td><%=v.getImporte() %></td>
	<!-- aumentamos el contador con cada libro para saber las unidades que tenemos en esta compra-->
	<% contador++; %>

	
	</tr>
<% } %>
		

<tr>
<td> UDS  </td><td>  </td><td> TOTAL </td><td>PVP </td>
</tr>
<tr>
<td>**  </td><td> ** </td><td> ** </td><td>** </td>
</tr>
<% 
//recogemos el importe de los libros de esta compra
double importe = venta.importeVenta(usuario);
%>
<tr>
<!-- mostramos unidades e importe de la compra -->
<td><%=contador %>  </td><td>  </td><td>  </td><td><%=importe %> </td>


</tr>

</table>
<%
// si no tiene ningun libro comprado nos manda un mensaje 
} else{%>

<h3>Aun no tienes ningun Libro comprado</h3>


<%} %>

<!-- nos redirecciona a la lista de libros para seguir comprando  -->
<a href="listarTienda.jsp?usuario=<%=usuario %>"><input type="button" value="Seguir comprando"></a>







</body>
</html>