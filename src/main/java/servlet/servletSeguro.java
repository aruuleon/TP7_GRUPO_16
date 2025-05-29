package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SeguroDao;
import daoImpl.SeguroDaoImpl;
import dominio.Contratacion;
import dominio.Seguro;
import dominio.TipoSeguro;
import dominio.Usuario;



/**
 * Servlet implementation class servletSeguro
 */
@WebServlet("/servletSeguro")

public class servletSeguro extends HttpServlet  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletSeguro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("Param") != null && request.getParameter("Param").equals("3"))
		{
			//Entra por haber echo click en el hyperlink listar usuarios
			// ACA 
			SeguroDaoImpl seguroDaoImplementacion = new SeguroDaoImpl();
			List<Seguro> listaSeguros = seguroDaoImplementacion.readAll();
			
			request.setAttribute("listaSeg", listaSeguros);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("ListarSeguros.jsp");   
	        rd.forward(request, response);
		}
		
		
		if(request.getParameter("Param") != null && request.getParameter("Param").equals("1"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("Inicio.jsp");   
	        rd.forward(request, response);
		}
		
		if(request.getParameter("Param") != null && request.getParameter("Param").equals("2"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("AgregarSeguro.jsp");   
	        rd.forward(request, response);
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
