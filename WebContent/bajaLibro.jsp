<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- funciones JS-->
<script type="text/javascript">
// funcion que se ejecuta en la lectura de la pagina
window.onload=function() {
	
	  // recoge el dato "isnbLista" que llega en la URL y lo guarda 
	  //en la variable 'dato'
	var queryString = window.location.search;
    var urlParams = new URLSearchParams(queryString);
    var dato = urlParams.get('isnbLista');
	// seleciona el elemento con id 'isnb' del html y le cambia el valor 
	// por el el valor de dato
	document.getElementById('isnb').value = dato;

	// lanza la funcion buscar()
	buscar()
	



}
// funcion que nos retorna un objeto Ajax
function getXMLHTTPRequest() {
	
	  try {
		req = new XMLHttpRequest();
		} catch(err1) {
		try {
		 req = new ActiveXObject("Msxml2.XMLHTTP");
		 } catch (err2) {
	       try {
	      req = new ActiveXObject("Microsoft.XMLHTTP");
		     } catch (err3) {
		      req = false;
	         }
		  }
		}
		

		return req;
		} 


// creamos nuestro objeto ajax al que llamamos llamada
var llamada =  getXMLHTTPRequest();

// funcion que genera una peticion asincrona para buscar un titulo en base de datos
// y nos retorne la respuesta el server si refrescar todo 
function buscarTitulo() {
	
	// se recoge el valor del input con id 'buscarTitulo'
	var nombre = document.getElementById('buscaTitulo').value;
	// direccion del controlador al que se le hace la peticion
	var myurl = 'ServletControlador';
	 // valor random fake para cambiar las peticiones en la url
	 fake = parseInt(Math.random()*999999999999999);
	 // composicion de la url
	 newurl = myurl+'?opcion=5&fake='+fake+'&nombre='+nombre;
	 // envio atraves de nuestro objeto ajax de la peticion 
	 // y envio de la respuesta a la funcion 'respuesta()'
	 llamada.open("GET", newurl, true);
	 llamada.onreadystatechange =respuesta 
	 llamada.send(null);
	
	
		
	
	}
	







//funcion que genera una peticion asincrona para buscar un libro por isnb en base de datos
//y nos retorne la respuesta el server si refrescar todo 

function buscar() {
	

	// se recoge el valor del input con id 'isnb'
	
	 isnb = document.getElementById('isnb').value;
	// direccion del controlador al que se le hace la peticion 
	var myurl = 'ServletControlador';
	 //valor random fake para cambiar las peticiones en la url
	 fake = parseInt(Math.random()*999999999999999);
	// composicion de la url
	 newurl = myurl+'?opcion=3&fake='+fake+'&isnb='+isnb;
	// envio atraves de nuestro objeto ajax de la peticion 
	 // y envio de la respuesta a la funcion 'respuesta()'
	 llamada.open("GET", newurl, true);
	 llamada.onreadystatechange =respuesta 
	 llamada.send(null);
	
	
		
	
	}
//funcion que genera una peticion asincrona para cambiar el presio de un libro en base de datos
//y nos retorne la respuesta el server si refrescar todo 


	
// metodo al que derivamos cualquier respuesta asincrona que nos devuelva un Json con un libro
function respuesta() {
	
// se comprueba que la respuesta en ajax esta en estado 4 o final y cuando es asi 
// se comprueba que el status es 200 ose que es correcta
	if(llamada.readyState == 4){
		
		if (llamada.status == 200){
			// se parsea el json a variables 
			var json = JSON.parse(llamada.responseText);
			var titulo = json.nombre;
			var autor = json.autor;
			var price = json.precio;
			var isnb = json.isnb;
			var idnum = json.id;
			// se entraga esas variables a los distintos inputs
			document.getElementById('titulo').value = titulo;
			document.getElementById('isnb').value = isnb;
		    document.getElementById('autor').value = autor;
			document.getElementById('precio').value = price;
			document.getElementById('idnumber').value = idnum;




 			
			

		}
	}
	
}

function Precio() {
	

	
//se recoge el valor del input con id 'isnb'

precio = document.getElementById('nuevoPrecio').value;
precio.trim();
id = document.getElementById('idnumber').value;
// direccion del controlador al que se le hace la peticion 
var myurl = 'ServletControlador';
//valor random fake para cambiar las peticiones en la url
fake = parseInt(Math.random()*999999999999999);
// composicion de la url
newurl = myurl+'?opcion=4&fake='+fake+'&precio='+precio+'&id='+id;
// envio atraves de nuestro objeto ajax de la peticion 
// y envio de la respuesta a la funcion 'respuesta()'
llamada.open("GET", newurl, true);

llamada.send(null);
 
buscar()

}



</script>


</head>
<body>


<!-- link a Menu de inicio -->
<a href="indexLibrero.jsp" ><input type="button" value="Menu Inicio"> </a>

<br>
<br>

<h3>   Menu de Bajas Libro </h3> <br>


	<label>
		Introduzca el ISNB del Libro
	</label>
	<ul>
		
<!-- imput para busqueda poe isnb o titulo  en peticion asincrona nos retornara un json a nuestra 
funcion JS -->
		<li>
			<label>ISNB</label>
			<input type="text" name="campodato" id="isnb" autocomplete="off">
			<a href="javascript:buscar()"><input type="button" value="BUSCAR ISNB"></a> 



		</li>
		<li>
			<label>TITULO</label>
			<input type="text" name="buscaTitulo" id="buscaTitulo"autocomplete="off">
			<a href="javascript:buscarTitulo()"><input type="button" value="BUSCAR TITULO"></a> 
			
			

		</li>

	</ul>
	
	<!-- formulario para cambiar el precio o borrar el libro algunos imput son readonly no modificables -->
<form action="ServletControlador" name=" formDelete" id="formuBorrar" method="GET" >
<ul>
<li>
	<label>ID</label>
	<input type="text" name="identy"id="idnumber" value="ID" readonly>

</li>
<li>
<label>Titulo</label>
<input type="text" name="nombre"id="titulo" value="titulo" readonly>

</li>
<li>
	<label>autor</label>
<input type="text" name="author"id="autor" value="autor" readonly>


</li>

<li>
	<label>ISNB</label>
	<input type="text" name="codigo"id="isnb" value="isnb" readonly>

</li>
<li>
	<label>precio</label>
	<input type="text" name="price"id="precio" value="precio" readonly>
	<input type="hidden" name="opcion" value="2">

	
	<label>.       Introduzca un nuevo precio    .</label>
	<input type="text" name="newPrice" id="nuevoPrecio" autocomplete="off" >


    
	<a href="javascript:Precio()"><input type="button" value="CAMBIAR PRECIO"></a> 


	
   

</li>
<li>
<!-- boton submit para enviar el formulario y borrar el libro  -->
    <input type="submit" name="enviar" value="BORRAR LIBRO">


</li>
 
</ul>


</form>

<br>


</body>
</html>