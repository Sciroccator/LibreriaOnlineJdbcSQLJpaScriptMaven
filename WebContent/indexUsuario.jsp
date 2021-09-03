<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


     <!-- nos da la opcion de volver al menu principal -->
   <a href="indexPrincipal.jsp" >   
   <input type= "button" name ="inicio" value= "Volver Inicio" id="IndexPrincipal" >
   </a>
<ul>


<!-- menu de usuarios para logarse o darse de alta  -->
<li>
<form action="GestionLogin" name= "formLogin">
<label> Uds ya es usuario de la Biblioteca</label>
<!-- si ya somos usuarios nos redirecciona al controlador para  logarnos -->
<input type= submit name= "acceso" value= "acceder">
<input type= hidden name = "opcion" value="1">

</form>

</li>
<li>


<label>
Registro nuevo Usuario 
</label>
     <!-- si no estamos dados de alta nos evia ha registrarnos a la pagina 'registroUsuario.jsp'-->
   <a href="registroUsuario.jsp" >   
   <input type= "button" name ="registro" value= "Registrarse" id="registry" >
   </a>
     
     
</li>

     
     
</li>





</ul>


</body>
</html>