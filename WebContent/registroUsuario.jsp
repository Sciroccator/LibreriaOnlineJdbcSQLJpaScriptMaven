<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- boton para volver al indice -->

<a href="indexUsuario.jsp"> <input type="button" name= "indice"value= "Inicio"></input></a>
<!-- formulario para registrar al usuario nuevo  conecta con el controlador 'ServletControladorUser -->
<form action="ServletControladorUser" name="formulario" method="post" id="registry">
<ul>
<li>
<!-- input para recibir el nombre e input oculto con el la opcion para el controlador -->
<label> Nombre</label>
<input type= "hidden" name="opcion" value=1>
<input type= "text" name="nombre" id="name" autocomplete="off">

</li>
<li>
<!-- inputs para recibir el apellido , email y password -->
<label> Apellidos</label>
<input type= "text" name="apellidos" id="secondName" autocomplete="off">

</li>
<li>
<label> Email </label>
<input type= "text" name="email" id="mail" autocomplete="off">
</li>


<li>
<label> Password </label>
<input type= "text" name="password" id="pass" autocomplete="off">
</li>
<li>
<!-- submit para enviar el formulario a el controlador  -->
<input type= "submit" name="grabar" value="Save" >

</li>


</ul>


</form>

</body>
</html>