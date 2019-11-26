package org.springframework.samples.petclinic;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class JDBCApplication {

	public static void main(String[] args) {
		System.out.println("-------- Test de conexión con MySQL ------------");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No encuentro el driver en el Classpath");
			e.printStackTrace();
			return;
		}

		System.out.println("Driver instalado y funcionando");
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preStatament = null;
		ResultSet rs = null;
		String sql = null;
		
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic","root", "root");
			if (connection != null)
				System.out.println("Conexión establecida");
				
				statement = connection.createStatement();
				//Ejercicio 2
//				sql = "Insert into owners (first_name, last_name, address, city, telephone) values ('Manuel', 'Melero', 'Costa y llobera', 'Sevilla','608707624') ";
//				statement.executeUpdate(sql);
				
				//Ejercicio 1
//				sql = "Select * From owners";
//				rs = statement.executeQuery(sql);
//				while(rs.next()) {
//					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " su dirección " + rs.getString(4) + " de la ciudad " + rs.getString(5) + " y su teléfono " + rs.getString(6));
////					System.out.println(rs.getInt("id") + " " + rs.getString("first_name") + " " + rs.getString("last_name") + " su dirección " + rs.getString("address") + " de la ciudad " + rs.getString("city") + " y su teléfono " + rs.getString("telephone"));
//				}
//				rs.close();
				
				//Ejercicio 3
//				sql = "Update owners set city = 'Madrid' where first_name = 'Manuel' and last_name = 'Melero' ";
//				statement.executeUpdate(sql);
				
				//Ejercicio 4
				String nombre = getNombre("nue");
				sql = "Select * From owners where first_name like ? or last_name = ?";
				preStatament = connection.prepareStatement(sql);
				preStatament.setString(1, nombre);
				preStatament.setString(2, nombre);
				rs = preStatament.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " su dirección " + rs.getString(4) + " de la ciudad " + rs.getString(5) + " y su teléfono " + rs.getString(6));
//					System.out.println(rs.getInt("id") + " " + rs.getString("first_name") + " " + rs.getString("last_name") + " su dirección " + rs.getString("address") + " de la ciudad " + rs.getString("city") + " y su teléfono " + rs.getString("telephone"));
				}
				rs.close();
			
			
			
			
		
			
			} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		} finally {
			try {
				if(statement != null)
					connection.close();
			} catch (SQLException se) {
		    	  
		    }
		    try {
		        if(connection != null)
		            connection.close();
		    } catch (SQLException se) {
		         	se.printStackTrace();
		    }
		}
		
		
		
		
		
		
	}

	public static String getNombre(String nombre) {
		return '%' + nombre + '%';
	}
}