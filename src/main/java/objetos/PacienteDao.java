package objetos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.MysqlConexion;

public class PacienteDao {

	
	
	private static List<Paciente> pacientes = new ArrayList<>();
	
	private static int  nextId = 1;
	
	
	 Connection con = MysqlConexion.conectar();

	public void agregarPaciente(Paciente paciente) {
		
		
		
		paciente.setId(nextId++);
		pacientes.add(paciente);
	    	
	    
	    PreparedStatement ps;
	    
	    try {
	    	
		    String query = "INSERT INTO Pacientes(Nombre, Fecha, Sexo, Telefono, Direccion, Consulta) VALUES (?, ?, ?, ?, ?, ?)";

			ps = con.prepareStatement(query);
			
		    
		    ps.setString(1, paciente.getNombre());    
		    ps.setString(2, paciente.getFecha());    
		    ps.setString(3, paciente.getSexo());     
		    ps.setString(4, paciente.getTelefono()); 
		    ps.setString(5, paciente.getDireccion());
		    ps.setString(6, paciente.getConsulta()); 

		    // Ejecución de la consulta SQL
		 int valorEjecucion=    ps.executeUpdate();
			
		 System.out.println("valor ejecucion" + valorEjecucion);
			
		 
		 
		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	 
		
	}
	
	
	
	
	
	
	public Paciente  obtenerPacientePorId(int id) {
		for(Paciente p : pacientes) {
			if(p.getId() == id) {
				
				return p;
			}
			
		}
		
		
		return null;
		
		
		
	}
	public List<Paciente> obtenerTodosLosPacientes() {
        return pacientes;
    }
	
}
