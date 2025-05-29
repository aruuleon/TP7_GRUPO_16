<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="daoImpl.TipoSeguroDaoImpl, dominio.TipoSeguro, java.util.ArrayList, java.util.Map, java.util.HashMap" %>
<%@ page import="daoImpl.SeguroDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar Seguro</title>
<style>
    .form-group { margin-bottom: 15px; }
    label { display: inline-block; width: 200px; }
    input, select { width: 250px; padding: 5px; }
</style>
<%
Map<String, String> valoresAnteriores = (Map<String, String>) session.getAttribute("valoresAnteriores");
if (valoresAnteriores == null) {
    valoresAnteriores = new HashMap<>();
    valoresAnteriores.put("descripcion", "");
    valoresAnteriores.put("idTipo", "");
    valoresAnteriores.put("costoContratacion", "");
    valoresAnteriores.put("costoAsegurado", "");
}
%>
<script>
    function validarNumeros(input) {
        input.value = input.value.replace(/[^0-9.]/g, '');
    }
</script>
<% 
SeguroDaoImpl seguroDao = new SeguroDaoImpl();
int proximoId = seguroDao.obtenerProximoIdDisponible();
%>
</head>
<body>
	<a href=servletSeguro?Param=1> Inicio</a> <br>
	<a href=servletSeguro?Param=3> Listar Seguro</a> <br>

	<h1>Agregar seguros</h1>
	<form action="servletGuardarSeguro" method="post">
		<div class="form-group">
            <label for="idSeguro">ID Seguro:</label>
      		<input type="text" id="idSeguro" name="idSeguro"
      		value="<%= proximoId %>" readonly
            class="form-control" style="background-color: #f8f9fa; ">  
        </div>
        
        <div class="form-group">
        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" name="descripcion" 
               value="<%= valoresAnteriores.get("descripcion") %>" required>
    </div>
    
    <div class="form-group">
        <label for="idTipo">Tipo de Seguro:</label>
        <select id="idTipo" name="idTipo" required>
            <option value="">-- Seleccione --</option>
            <% 
            TipoSeguroDaoImpl tipoDao = new TipoSeguroDaoImpl();
            ArrayList<TipoSeguro> tipos = tipoDao.obtenerTodosLosTiposDeSeguro();
            String idTipoSelected = valoresAnteriores.get("idTipo");
            
            for (TipoSeguro tipo : tipos) {
            	String selected = String.valueOf(tipo.getIdTipo()).equals(idTipoSelected) ? "selected" : "";            %>
                <option value="<%= tipo.getIdTipo() %>" <%= selected %>>
                    <%= tipo.getDescripcion() %>
                </option>
            <% } %>
        </select>
    </div>
    
    <div class="form-group">
        <label for="costoContratacion">Costo de Contratación:</label>
        <input type="text" id="costoContratacion" name="costoContratacion" 
               value="<%= valoresAnteriores.get("costoContratacion") %>" required>
    </div>
    
    <div class="form-group">
        <label for="costoAsegurado">Costo Máximo Asegurado:</label>
        <input type="text" id="costoAsegurado" name="costoAsegurado" 
               value="<%= valoresAnteriores.get("costoAsegurado") %>" required>
    </div>
    
    <button type="submit" class="btn btn-primary">Guardar Seguro</button>
	</form>
</body>
</html>