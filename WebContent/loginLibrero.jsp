<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   
  <!-- boton para volder al indice principal -->
  <a href="indexPrincipal.jsp"> <input type="button" value="volver inicio">  </a>

<% 

String mensaje = (String)request.getAttribute("mensaje"); 
if (mensaje == null || mensaje == ""){
mensaje = "Login Librero";
}%>
<!--pintamos el mensaje que nos envia el controlador o el mensaje por defecto-->
<h3> <%= mensaje %></h3>
<!-- formulario para el login de Libreros  conecta con el controlador 'GestionLogin' -->
<form action="GestionLogin" name = "formlogin" id ="formulariologin">

<ul>
<li>
<!-- Input login recoge el login que le manda el controlador que a su vez a recopilado de 
la cookie de la pagina -->
<label> Introduzca su Login</label>
<input type= "text" name="login" id= "log" value = "<%= request.getAttribute("login")   %>   "autocomplete="off" >

</li>
<li>
<!-- input del password e input hidden con la opcion para el controlador -->
<label> Introduzca el Password</label>
<input type= "text" name = "password"id = "pass"autocomplete="off">
<input type= "hidden" name = "opcion" value="4" id = "option">
</li>
<li>
<!-- boton submit del formulario  -->
 <input type= "submit" name= " submitlogin" value = "enviar" id =" submitlog">
</li>

  
  
     




</ul>


 </form>













</body>
</html>