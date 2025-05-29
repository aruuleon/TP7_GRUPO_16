package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.SeguroDaoImpl;
import dominio.Seguro;

@WebServlet("/servletGuardarSeguro")
public class servletGuardarSeguro extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        List<String> errores = new ArrayList<>();
        
        String descripcion = request.getParameter("descripcion");
        String idTipoStr = request.getParameter("idTipo");
        String costoContratacionStr = request.getParameter("costoContratacion");
        String costoAseguradoStr = request.getParameter("costoAsegurado");
        
        if (descripcion == null || descripcion.trim().isEmpty()) {
            errores.add("La descripción es requerida");
        }
        
        int idTipo = 0;
        double costoContratacion = 0;
        double costoAsegurado = 0;
        
        try {
            idTipo = Integer.parseInt(idTipoStr);
        } catch (Exception e) {
            errores.add("Seleccione un tipo de seguro válido");
        }
        
        try {
            costoContratacion = Double.parseDouble(costoContratacionStr.replace(",", "."));
        } catch (Exception e) {
            errores.add("Costo de contratación debe ser un número válido");
        }
        
        try {
            costoAsegurado = Double.parseDouble(costoAseguradoStr.replace(",", "."));
        } catch (Exception e) {
            errores.add("Costo asegurado debe ser un número válido");
        }
        
        if (!errores.isEmpty()) {
            request.getSession().setAttribute("errores", errores);
            request.getSession().setAttribute("valoresAnteriores", Map.of(
                "descripcion", descripcion != null ? descripcion : "",
                "idTipo", idTipoStr != null ? idTipoStr : "",
                "costoContratacion", costoContratacionStr != null ? costoContratacionStr : "",
                "costoAsegurado", costoAseguradoStr != null ? costoAseguradoStr : ""
            ));
            response.sendRedirect("AgregarSeguro.jsp");
            return;
        }
        
        Seguro nuevoSeguro = new Seguro();
        nuevoSeguro.setDescripcion(descripcion);
        nuevoSeguro.setIdTipo(idTipo);
        nuevoSeguro.setCostoContratacion(costoContratacion);
        nuevoSeguro.setCostoAsegurado(costoAsegurado);
        
        try {
            SeguroDaoImpl seguroDao = new SeguroDaoImpl();
            if (seguroDao.insert(nuevoSeguro)) {
                request.getSession().setAttribute("mensaje", 
                    "Seguro guardado exitosamente con ID: " + nuevoSeguro.getIdSeguro());
            } else {
                errores.add("Error al guardar en la base de datos");
                request.getSession().setAttribute("errores", errores);
            }
        } catch (Exception e) {
            errores.add("Error interno del sistema: " + e.getMessage());
            request.getSession().setAttribute("errores", errores);
        }
        
        response.sendRedirect("AgregarSeguro.jsp");
    }
}
