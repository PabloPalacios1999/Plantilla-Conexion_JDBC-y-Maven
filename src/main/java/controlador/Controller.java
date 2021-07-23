package controlador;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TestDAO dao;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	String jdbcURL = getServletContext().getInitParameter("jdbcURL");
    	String jdbcUser = getServletContext().getInitParameter("jdbcUser");;
    	String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
    	System.out.println(jdbcURL);
    	System.out.println(jdbcUser);
    	System.out.println(jdbcPassword);
    	try {
			dao = new TestDAO(jdbcURL, jdbcUser, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("option");
		RequestDispatcher redireccionar;
		
		
		switch (op) {
			case "registrar":
				
				Articulo objArticulo1 = new Articulo(0, "Televisor", "Muy buen equipo", 507);
	
				if(dao.registrar(objArticulo1)) {
					System.out.println("Registrado");
				}else {
					System.out.println("No registrado");
				}
				
				break;
				
			case "consultar":
				dao.consultar();
				break;
				
			case "editar":
				
				Articulo objArticulo2 = new Articulo();
				objArticulo2.setNombre("C5 alas");
				objArticulo2.setId(3);
				if(dao.editar(objArticulo2)) {
					System.out.println("editado");
				}else {
					System.out.println("No editado");
				}
				break;
				
			case "eliminar":
				Articulo objArticulo = new Articulo();
				objArticulo.setId(1);
				if(dao.eliminar(objArticulo)) {
					System.out.println("eliminado");
				}else {
					System.out.println("No eliminado");
				}
				break;

			default:
				System.out.println("Escoja una opcion valida");
				break;
		}
		
		
		
		
		redireccionar = request.getRequestDispatcher("/index.jsp");
		redireccionar.forward(request, response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
