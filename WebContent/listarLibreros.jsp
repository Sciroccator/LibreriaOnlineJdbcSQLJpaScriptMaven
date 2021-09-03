<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.List" %>   
<%@ page import = "dao.DaoLibrero" %>   
<%@ page import = "modelo.Librero" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- redireccionamos al indice de libreros  -->

<a href="indexLibrero.jsp" ><input type="button" value="Menu Inicio"> </a>
<ul>

<li>
<h3>
Listado de Libreros
</h3>

</li>

</ul>
<table border= 1px>
<!-- tabla para ver los libreros que existen en BD -->
<tr>
<th>ID</th><th>LOGIN</th><th>PASSWORD</th>
</tr>
<%
// el controlador nos envia la lista de libreros  la parseamos y la guadamos en un list
List<Librero> lista = (List<Librero>)request.getAttribute("listaLibreros");
%>



<%
// iteramos la lista y la pintamos en la tabla 
for(Librero e :  lista){
%>

    <tr> 
                    <td> <%= e.getId() %></td>
                    <td> <%= e.getLogin() %></td>
                    <td> <%= e.getPasword() %></td>
                   
                  
              
    </tr>    

<%} %>






</table>









</body>
</html>