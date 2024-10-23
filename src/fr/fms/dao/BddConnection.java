package fr.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BddConnection {
	   
		// on créee la connexion avec une variable d'instance
		   private static Connection connection = null;
		
		   public static Connection Connect(){
			   boolean isValidAdmin = false; 
			   // si la connection vaut null(c'est a dire qu'elle n'est pas effective, on crée la connection)
			   if(connection == null) {
				   
						try {
								
								Class.forName("org.mariadb.jdbc.Driver");
							}catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						
						try {
							
							String url = "jdbc:mariadb://localhost:3306/Eval";
							String user = "root";
							String pwd = "fms2024";
							
							connection = DriverManager.getConnection(url, user, pwd);
							//System.out.println("Connection effectué");
							isValidAdmin = true; 
							return connection; 
							
						}catch (SQLException e) {
							e.printStackTrace();
							if(!isValidAdmin) {
								System.out.println("Acces refusé");
							}
							
						}
				
			   }
			   return connection; 
		   }

}
