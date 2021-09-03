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
  <br>
  <br>

<!-- menu de los libreros que nos da opcion a crear libros , modificarlos, listar usuarios de la web y 
listar los libreros que tienen acceso a hacer modificaciones en la aplicacion  -->
<label> Menu Opciones Librero </label>

<ul>

<li>
<!-- te redirecciona para dar de alta un libro  -->
<label>Alta Libros</label>
<a href="altaLibro.jsp" ><input type="button" value="Alta Libro"> </a>

</li>

</li>
<li>
<!-- te redirecciona para poder modificar un libro -->
<label>Listar y Modificar Libros</label>
<a href="listarLibros.jsp" ><input type="button" value="Listar Modificar Libros"> </a>
</li>
<li>
<label>
Listar Usuarios 
</label>
 <!-- te envia al controlador  para listar los usuarios de la web -->    
   <a href="ServletControladorUser?opcion=2" >   
   <input type= "button" name ="listar" value= "Listar Usuarios" id="list" >
   </a>
</li>    
<li>
<label>
Listar Libreros 
</label>
   <!-- te envia al controlador para listar los Libreros con accesso -->  
   <a href="ServletControladorUser?opcion=3" >   
   <input type= "button" name ="listarLibreros" value= "Listar Libreros" id="listLibreros" >
   </a>
</li>      

</ul>
   



</body>
</html>