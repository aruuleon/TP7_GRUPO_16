<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href=servletSeguro?Param=1> Inicio</a> <br>
	<a href=servletSeguro?Param=2> Agregar Seguro</a> <br>
	<a href=servletSeguro?Param=3> Listar Seguros</a> <br>
	
 	<h1>Tipo de Seguros de la base de datos</h1>
 	
 Busqueda por tip de seguro 
	 <select name= "seguro">			
				<option>Seguro de casas</option>
				<option>Seguro de vida</option>
				<option>Seguro de motos</option>
	</select> 

<br>
<table border="1">
	<tr>  <th>ID seguro</th>  
		  <th>Descripcion Seguro</th>   
		  <th>Tipo de Seguro</th> 
		  <th>Costo Contratacion</th> 
		  <th>Costo Max Asegurado</th> 
	</tr>
</table>
</body>
</html>