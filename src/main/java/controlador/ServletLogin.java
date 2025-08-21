package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.MysqlConexion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import com.google.gson.Gson;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		
		
		
		boolean usuarioEncontrado = false;
		
		
		String QUERY="select*from Usuarios WHERE correo = ? AND contrasena = ?";

		
		
		
		
		
		try (Connection con =   MysqlConexion.conectar()){
			
	
			
			// statement preparado 
			PreparedStatement psdt = con.prepareStatement(QUERY);
		psdt.setString(1,correo);
		psdt.setString(2,contrasena);
			
			
			
			// ejecucion devuelve una tabla con la conuslta realizada 
			ResultSet rs= psdt.executeQuery();
			
			
			
			String nombre_usuario="";
			
			
			
			//iteramos la tabla 
			while(rs.next()) {
				usuarioEncontrado = true;
						
				nombre_usuario=  rs.getString("Nombre");
				break;
			}
			
			

	        Map<String, Object> respuesta = new HashMap<>();

	        if (usuarioEncontrado) {
	            respuesta.put("estado", true);
	            respuesta.put("mensaje", "¡Bienvenido!");
	            respuesta.put("nombre_usuario",nombre_usuario);
	            
	            
	        } else {
	            respuesta.put("estado", false);
	            respuesta.put("mensaje", "Credenciales incorrectas.");
	        }

	        Gson gson = new Gson();
	        String jsonResponse = gson.toJson(respuesta);
	        
	        
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        PrintWriter out = response.getWriter();

	        out.print(jsonResponse);
	        out.flush();
	        
	       System.out.println(jsonResponse);
			
			
			
			
			
			
		} catch (Exception e) {
		    System.out.println("consultar Error"+e.getMessage());
		    e.printStackTrace();
		

		
		}
		
		
		

	}

}
