package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import objetos.Usuario;
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
	     String accion = request.getParameter("accion");
	        
	        if ("mostrarRegistro".equals(accion)) {
	            // Lógica para redirigir a la página de registro
	            request.getRequestDispatcher("/Registro1.jsp").forward(request, response);
	        }
	
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
			
			
			
			Usuario usuario = null;
			
			
			//iteramos la tabla 
			if(rs.next()) {
				usuarioEncontrado = true;
				// se instancia un objeto de tipo usuario para almeacenar los datos 
				usuario = new Usuario();
				usuario.setId( rs.getInt("id")  );				
				usuario.setNombre(rs.getString("nombre"));
				usuario.setCorreo(rs.getNString("correo"));
				usuario.setContrasena(rs.getString("contrasena"));
			}
			
			

	        Map<String, Object> respuesta = new HashMap<>();

	        if (usuarioEncontrado) {
	        	
	        	
                HttpSession session = request.getSession();
                
                // se guardan los datos de la sesion 
                session.setAttribute("usuarioLogeado", usuario); 
	            respuesta.put("estado",true);
                respuesta.put("mensaje", "¡Bienvenido, " + usuario.getNombre() + "!");
	            respuesta.put("nombre_usuario",usuario.getNombre());
	            


	            
	            
	        } else {
	            respuesta.put("estado", false);
	            respuesta.put("mensaje", "Credenciales incorrectas.");
	        }

	        
	        // se utiliza la libreria gson para poder enviarlo al ajax
	        Gson gson = new Gson();
	        // se  realiza una conversion con el metodos(TOJSON)  -SERIALIZACION
	        String jsonResponse = gson.toJson(respuesta);
	        
	        //SE  DEFINE EL PARAMETRO DE RESPUESTA SERVIDOR- CLIENTE   
	        
	        response.setContentType("application/json");  // ENCABEZADO HTTP 
	        response.setCharacterEncoding("UTF-8");//ENVIO DE CARACTERES ESPECIALES
	        



	        
	        PrintWriter out = response.getWriter();
/*
 *INSTANCIAR UN OBJETO DE TIPO PRINTWRITER PARA PODER UTILIZAR SUS METODOS
 *Y  PSOTUMO ESCRIBIR EN EL CUERPO DE LA RESPUESTA ENVIADA POR EL PROTOCOLO HTTP 
 *
 */
	        
	        out.print(jsonResponse);
	        out.flush();
	        
	       System.out.println(jsonResponse);
			
			
			
			
			
			
		} catch (Exception e) {
		    System.out.println("consultar Error"+e.getMessage());
		    e.printStackTrace();
		

		
		}
		
		
		

	}

}
