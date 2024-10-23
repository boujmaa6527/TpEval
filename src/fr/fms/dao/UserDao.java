package fr.fms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Commande;
import fr.fms.entities.User;

public class UserDao implements Dao<User>{
	
	private ArrayList<User>  listUser; 
	private Connection connection; 
	private BddConnection bddConnection; 
	
	
	public UserDao(){
		listUser = new ArrayList<User>();
		
	}
	
		//enregiste une occurence dans la bdd
		@Override
		public boolean create(User obj) {
			// on appelle la fonciton static Connect de la class BddConnection
			//connection =  (Connection) BddConnection.Connect();
			
			try(Statement statement = connection.createStatement()){
				String str = "INSERT INTO t_users (login, password)"
						+ "VALUES ('" + obj.getLogin() +"' ,'"+ 
										obj.getPassword()+");";
				int row = statement.executeUpdate(str);
				if(row == 1) 	System.out.println("Insertion ok");
				return true; 
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return false; 
		}
		// return un objet Formation grace a son id 
		@Override
		public User read(int IdUser) {
			// on appelle la fonciton static Connect de la class BddConnection
			connection =  (Connection) BddConnection.Connect();
				try {
					//on prepare la requete avec l'id comme condition car nous recherchons par Id
					PreparedStatement preparedSatatement = connection.prepareStatement("SELECT * FROM t_users WHERE IdUser = ? ");
					preparedSatatement.setInt(1, IdUser);
					
					//on execute la requete
					ResultSet result = preparedSatatement.executeQuery();
					
					//Pas de boucle si nous recherchon par Id
					if(result.next()) {
						return new User(
								result.getInt("idUser"),
								result.getString("login"),
								result.getString("password"));
								
								
					}
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
				return null;
		}
		//mise à jour de l'objet articles grace a son id
		@Override
		public boolean update(User obj) {
			// on appelle la fonciton static Connect de la class BddConnection
			connection =  (Connection) BddConnection.Connect();
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE t_users SET login = ?, password = ? WHERE idUser = ? ");
				preparedStatement.setString(1, obj.getLogin());
				preparedStatement.setString(1, obj.getPassword());
				preparedStatement.setInt(3, obj.getIdUser());
				
				//on return true si l'update a été effectué
				return preparedStatement.executeUpdate() == 1;
			}catch(SQLException e) {
				e.printStackTrace();
			}
			//sinon on return false
			return false;
		}
		@Override
		public boolean delete(int IdUser) {
			// on appelle la fonciton static Connect de la class BddConnection
			connection =  (Connection) BddConnection.Connect();
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_users WHERE idUser = ?");
				preparedStatement.setInt(1, IdUser);
				// return true si return 1
				return preparedStatement.executeUpdate() == 1;
				 
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return false; 
		}
		// on obtient la liste des articles
		@Override
		public ArrayList<User> readAll(){
			// on appelle la fonciton static Connect de la class BddConnection
			ArrayList<User> listUser = new ArrayList<User>(); 
			
			connection =  (Connection) BddConnection.Connect();
			try {
				//on prepare la requete
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_commandes");
				//on execute la requete avec ResultSet
				ResultSet result = preparedStatement.executeQuery();
				//on parcour les resultats avec une boucle while
				while(result.next()) {
					//on recupere get les donnes des propriété
					User user = new User(
							result.getInt("idUser"),
							result.getString("login"),
							result.getString("password"));
							
					//on ajoute les données a la liste
					listUser.add(user);
							
				}
				//on return la liste des commande
				return listUser; 
				
				
			}catch(SQLException e ) {
				e.printStackTrace();
			}
			return null; 
	 }


}
