<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<a href="indexUsuario.jsp"> <input type="button" value="volver inicio">  </a>
<% String mensaje = (String)request.getAttribute("mensaje");
if (mensaje == null || mensaje ==""){
mensaje = "Login Uusario";
}%>

<h3> <%=mensaje %></h3>
<!-- formulario para logar al usuario conecta con el controlador GestionLogin  -->
<form action="GestionLogin" name = "formlogin" id ="formulariologin">

<ul>
<li>
<!-- input para recoger el email tambien recibe el dato 'email' que le envia el controlador 
sacado de la cookie enviada por la misma pagina -->
<label> Introduzca su email</label>
<input type= "text" name="email" id= "mail" value = "<%= request.getAttribute("email")   %>   "autocomplete="off" >

</li>
<li>
<!-- recoge el password y la opcion a enviar al controlador  -->
<label> Introduzca el Password</label>
<input type= "text" name = "password"id = "pass"autocomplete="off">
<input type= "hidden" name = "opcion" value="2" id = "option">
</li>
<li>
 <input type= "submit" name= " submitlogin" value = "enviar" id =" submitlog">
</li>





</ul>


 </form>













</body>
</html>