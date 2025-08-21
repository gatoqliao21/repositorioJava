package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objetos.Pacientes;
import utils.MysqlConexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Servlet implementation class servletGestionPac
 */
@WebServlet("/servletGestionPac")
public class servletGestionPac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletGestionPac() {
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
	
		
		
		
		
		/*
         * devuelve un objeto de tipo bufferedReader
         * siendo este el que me permite leer el cuerpo de la 
         * solicitud HTTP  donde se encuentra el objeto JSON          
         * */
        BufferedReader reader = request.getReader();
		
		
		
		/*
		 * se crea una varibale para  manipular   cadenas de texto  de manera eficiente
		 * UNA VEZ QUE HAYA SIDO LEIDO 
		 * */
		 
        StringBuilder sb = new StringBuilder();
        
        
        
      String line;     
      
      
      
      
/*
 * se ejecuta el bucle while hasta que sea null
 * independient5e del tamaño del json enviado como solicitud 
 * enviado por http ,  se agrega al stringbuilder  sb 
 * para que sea manipulado de mejor manera 
 * 
 * */
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        
        // 2. Obtener la cadena JSON completa
        String jsonString = sb.toString();

        
        
        // imprimes todod el json recepcionado 
        System.out.println(jsonString);

        
        Gson gson = new Gson();
        
        
        Pacientes paciente = gson.fromJson(jsonString, Pacientes.class);
        
        
        
        String nombre = paciente.getNombre();
        String fecha = paciente.getFecha();
        String direccion= paciente.getDireccion();
        String sexo= paciente.getSexo();
        String telefono= paciente.getTelefono();
        String consulta = paciente.getConsulta();
        
        
        // validamos que todos los datos de paciente sean correctos 
           System.out.println(paciente.getDireccion());
           System.out.println(paciente.getNombre());
           System.out.println(paciente.getSexo());
           System.out.println(paciente.getTelefono());
           System.out.println(paciente.getConsulta());
        
		 Connection con = MysqlConexion.conectar();
       
		 try {
			    String query = "INSERT INTO Pacientes(Nombre, Fecha, Sexo, Telefono, Direccion, Consulta) VALUES (?, ?, ?, ?, ?, ?)";
			    
			    PreparedStatement ps;
			    
			    // Asumiendo que `con` ya está definida y es una conexión válida
			    ps = con.prepareStatement(query);

			    // Los números en setString() corresponden a la posición de los '?'
			    // El orden debe coincidir con el de las columnas en la consulta SQL
			    
			    ps.setString(1, nombre);    
			    ps.setString(2, fecha);    
			    ps.setString(3, sexo);     
			    ps.setString(4, telefono); 
			    ps.setString(5, direccion);
			    ps.setString(6, consulta); 

			    // Ejecución de la consulta SQL
			 int valorEjecucion=    ps.executeUpdate();
/*
 * se imprime el valor devuelto por el metodo executeUpdate 
 * 
 * */
			    System.out.println("valor ejecucion" + valorEjecucion);
			    response.getWriter().write("usuario registrado con exito");

			} catch (SQLException e) {
			    e.printStackTrace();
			    response.getWriter().write("ERROR EN EL REGISTRO" + e.getMessage());
			}
	
	//opcional 
/*
 * response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	PrintWriter out = response.getWriter();
	out.print(jsonString);
       	
			
			out.flush();	

 * 
 * */
		
    }
}
