package objetos;

public class Pacientes {

	String nombre;
    String fecha;
    String sexo;
    String telefono;
    String direccion;
    String consulta;
	public Pacientes(String nombre, String fecha, String sexo, String telefono, String direccion, String consulta) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.sexo = sexo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.consulta = consulta;
	}
	public Pacientes() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	
	
	
}
