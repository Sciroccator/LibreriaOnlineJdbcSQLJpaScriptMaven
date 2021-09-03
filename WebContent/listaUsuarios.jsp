<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.List" %>   
<%@ page import = "dao.DaoUsuario" %>   
<%@ page import = "modelo.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- boton para volver al idice libreros -->
<a href="indexLibrero.jsp" ><input type="button" value="Menu Inicio"> </a>
<ul>

<li>
<h3>
Listado de Usuarios
</h3>


</li>

</ul>
<%
List<Usuario> lista = (List<Usuario>)request.getAttribute("listaUsuarios");
%>

<%if (lista!=null){ %>
<table border= 1px>
<tr>
<th>ID</th><th>NOMBRE</th><th>APELLIDOS</th><th>EMAIL</th><th>PASSWORD</th>
</tr>




<%
for(Usuario e :  lista){
%>

    <tr> 
                    <td> <%= e.getUserID() %></td>
                    <td> <%= e.getNombre() %></td>
                    <td> <%= e.getApellidos() %></td>
                    <td> <%= e.getEmail() %></td>
                    <td> <%= e.getPass() %></td>
              
    </tr>    

<%} %>






</table>
<%}else{ %>

<h3>No hay ningun Usuario dado de alta en la base de datos</h3>
<%} %>







</body>
</html>