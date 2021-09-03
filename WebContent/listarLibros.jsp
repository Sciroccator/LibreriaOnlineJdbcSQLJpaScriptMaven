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
</head>
<body>

<!-- añadimos un boton para regresar al indice librero  -->
<a href="indexLibrero.jsp" ><input type="button" value="Menu Inicio"> </a>
<br>
<br>
    
<%
// recogemos los Libros de la BD en un List
 ArrayList<Libro>libros= Libro.listarLibros();
%>

<!-- pintamos una tabla para mostrar los libros  --> 	
<table border= 1px>
<tr style="background-color: aqua;"> <th>ID</th><th>TITULO</th> <th>ISNB</th> <th>AUTOR</th> <th>PRECIO</th>  </tr>

<%
// iteramos la lista y pintamos cada libro 
for(Libro l : libros){%>
<tr>
<td><%=l.getId() %></td>
<td><%=l.getNombre() %></td>
<td><%=l.getIsnb() %></td>
<td><%=l.getAutor() %></td>
<td><%=l.getPrecio() %></td>
<!-- por cada iteracion le añadimos un boton para consultar el libro y hacer modificaciones  -->
<td> <a href='bajaLibro.jsp?isnbLista=<%=l.getIsnb()%>'><input type="button" value="Consultar Libro"></a></td>

</tr>

<%}%>
</table>



</body>
</html>