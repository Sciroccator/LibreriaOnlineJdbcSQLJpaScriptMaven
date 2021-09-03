<%@page import="negocio.daoCarrito"%>
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
</head>
<body>

<!-- tabla para pintar el libro de la compra inmediata  -->
<table>
<tr>
<th>ID</th><th>TITULO</th><th>AUTOR</th><th>PRECIO</th>

</tr>

<%   
// variable para contabilizar los libros totales
int contador =0;
// variable para recoger la id del libro 
int id = 0;
// se comprueba que la id no es nula y se guarda en 'id'
// y se aunmenta el contador de libros 
if(request.getParameter("id")!=""){
	id = Integer.parseInt(request.getParameter("id"));
	contador++;
}
// se recoge el libro que pertenece a la id 
Libro l = new Libro().buscarId(id);


// se recoge el usuario que nos envia el controlador
String usuario= request.getParameter("usuario"); 
// se recogen los libros que tiene pendiente el usuario en el carrito para que los añada
//a la venta
Carrito carro = new Carrito();
ArrayList<Libro> libros= carro.FindByUsuario(usuario);

%>
<tr>
<!-- se pinta el libro de compra inmediata en la tabla -->
<td><%=id %> </td><td><%=l.getNombre() %></td><td><%=l.getAutor() %></td><td><%=l.getPrecio() %></td>
</tr>
</table>
<!-- si hay libros en el carrito se pintan en la tabla  -->
<%  if (libros != null){ %>
	<label> Libros pendientes en Carrito de compras </label>

	<table>
	<tr>
	<th>ID</th><th>TITULO</th><th>AUTOR</th><th>PRECIO</th>

	</tr>

<!--  se itera el list de los libros de el carrito y se pintan en la tabla  -->
<% for (Libro li : libros){%>
	
	<tr>
	<td><%=li.getId() %></td>
	
	<td><%=li.getNombre() %></td>
	<td><%=li.getAutor() %></td>
	<td><%=li.getPrecio() %></td>
	<% contador++; %>
	
	</tr>
		
<%}%>
<%}%>
<tr>
<!-- se sacan los totales de unidades y precio -->
<td> UDS  </td><td>  </td><td> TOTAL </td><td>PVP </td>
</tr>
<tr>
<td>**  </td><td> ** </td><td> ** </td><td>** </td>
</tr>
<% 
double precio = l.getPrecio();
double importe = carro.importe(usuario)+ precio ;
%>
<tr>
<td><%=contador %>  </td><td>  </td><td>  </td><td><%=importe %> </td>


</tr>

</table>
<!-- formulario para hacer el pago manda al controlador 'ServletTienda'el usuario el id del libro y la opcion 
del controlador  -->

<form action="ServletTienda" name="compraFinal" method="GET">

<input type="hidden" class="usuarios" name = "usuarioCompra" value= <%=usuario %>>
<input type="hidden" name = "opcion" value="5">
<input type="hidden" class="identificacion" name = "id" value= <%=id %>>


<input type="submit" name = "enviarCompra" value="PAGAR COMPRA">
   
  
</form>

<!-- boton para volver atras y seguir comprando  -->
<a href="listarTienda.jsp?usuario=<%=usuario %>"><input type="button" value="Seguir comprando"></a>


</body>
</html>