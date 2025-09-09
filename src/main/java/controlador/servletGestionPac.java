package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objetos.Paciente;
import objetos.PacienteDao;

import java.io.BufferedReader;
import java.io.IOException;


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
         * creas la instancia de un objeto bufferredReader y la guardas en la variable
         * reader        
         * */
        BufferedReader reader = request.getReader();
		
			/*
		 * crea una cadena de caracteres en el mismo objeto sin crear copias nuevas
		 * */
		 
        StringBuilder sb = new StringBuilder();     
        	String line;     
      
            
/*
 * se ejecuta el bucle while hasta que sea null
 * independient5e del tamaño del json enviado como solicitud 
 * enviado por http ,  se añade al stringbuilder  sb 
 * */
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            }
        // 2. se convierte en cadena de tipo String 
        String jsonString = sb.toString();
        // imprimes todod el json recepcionado 
        System.out.println(jsonString);
        Gson gson = new Gson();        
         Paciente paciente = gson.fromJson(jsonString, Paciente.class);
        
      /*
       * 
       *String nombre = paciente.getNombre();
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
         
                */  
        

		 PacienteDao pacienteDAO = new PacienteDao();

		 try {
			 pacienteDAO.agregarPaciente(paciente);

			 

			    response.getWriter().write("usuario registrado con exito");

			} catch (Exception e) {
			    e.printStackTrace();
			    response.getWriter().write("ERROR EN EL REGISTRO" + e.getMessage());
			}
	

		
    }
}
