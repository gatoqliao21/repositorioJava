package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		
		
		
		
	    JsonObject jsonResponse = new JsonObject(); // Objeto para la respuesta JSON

		Connection conn= null;
		PreparedStatement stmt = null;
		try {
            // 1. Carga del driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            
            String url= "jdbc:sqlserver://localhost:1433; databaseName=proyecto;encrypt=true;trustServerCertificate=true";
            // 2. Conexión a la base de datos con la URL corregida
            conn = DriverManager.getConnection(url,"sa","Innova2019.");

            // 3. Sentencia SQL para insertar los datos
            String sql = "INSERT INTO Usuarios(nombre, apellido, correo, contrasena) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // 4. Asignación correcta de parámetros
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, correo);
            stmt.setString(4, contrasena); // Se inserta la contraseña en texto plano

            // 5. Ejecución de la consulta
            stmt.executeUpdate();

            jsonResponse.addProperty("estado", true);
            jsonResponse.addProperty("mensaje", "Usuario registrado con éxito");

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.addProperty("estado", false);
            jsonResponse.addProperty("mensaje", "Error en el registro: " + e.getMessage());
        }  finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }

            // Envía la respuesta JSON, ya sea éxito o error
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse.toString());
        }
    }
    }
