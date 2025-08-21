package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlConexion {

	
	public static void main(String [] args) {
		Connection con = conectar();
		if(con!=null) {
			System.out.println("Conectado");
		}else {
			System.out.println("No conectado");
		}
	}

	public static Connection conectar() {
		Connection conn= null;
        String url= "jdbc:sqlserver://localhost:1433; databaseName=proyecto;encrypt=true;trustServerCertificate=true";
        		
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url,"sa","Innova2019.");
        	
        	System.out.println("conectado adecuadamente ");
        	
		} catch (ClassNotFoundException e) {
		
				System.out.println("mensaje de eror"+ e.getMessage());
			
		}catch (SQLException ex) {
			System.out.println("Mensaje de error"+ ex.getMessage());
			System.out.println("CODIGO de error"+ ex.getErrorCode());
			System.out.println("CODIGO DE ESTADO"+ ex.getSQLState());
		}
		
		
		
		
		
		
		return conn;
	}
	
	
}
