<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- hipervinculo a indice de libreros indexLibrero.jsp-->

<a href="indexLibrero.jsp" ><input type="button" value="Menu Inicio"> </a>


<!-- Formulario para recoger los datos del libro en inputs tipo texto 
que envia el formulario al controlador Servlet controlador -->
<form action="ServletControlador" name="guardar" method="post" id="insert">

<ul>
<li>
<label>
Titulo del Libro
</label>
<input type="text" name="nombre"id="name " autocomplete="off">

</li>

<li>
<label>
codigo ISNB 
</label>
<input type="text" name="isnb"id="isnbcode" autocomplete="off">

</li>
<li>
<label>
Autor del libro
</label>
<input type="text" name="autor"id="author" autocomplete="off">

</li>
<li>
<label>
Precio del Libro
</label>
<input type="text" name="precio"id="price " autocomplete="off">
<input type="hidden" name="opcion" value="1">
</li>


</ul>
<input type="submit" name="grabar" value="Guardar" id="submit">

</form>



</body>
</html>