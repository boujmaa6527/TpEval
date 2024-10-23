package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Client;
import fr.fms.entities.Formation;


			public class ClientDao implements Dao<Client>{
				
				private ArrayList<Client>  listClient; 
				private Connection connection; 
				private BddConnection bddConnection; 
				
				//contructeur pour la connexion a notre BDD le chemin, login et pwd
				public ClientDao(){
					listClient = new ArrayList<Client>();
					
				}
				
			//enregiste une occurence dans la bdd
			@Override
			public boolean create(Client obj) {
				// on appelle la fonciton static Connect de la class BddConnection
				//connection =  (Connection) BddConnection.Connect();
				
				try(Statement statement = connection.createStatement()){
					String str = "INSERT INTO t_clients (Nom, Prenom, Email, Adresse, Telephone, IdUser)"
							+ "VALUES ('" + obj.getNom() +"' ,'"+
											obj.getPrenom() +"' , "+ 
											obj.getEmail()+" , "+ 
											obj.getAdresse()+" , "+ obj.getTelephone()+ ", "+obj.getIdUser()+");";
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
			public Client read(int IdClient) {
				// on appelle la fonciton static Connect de la class BddConnection
				connection =  (Connection) BddConnection.Connect();
					try {
						//on prepare la requete avec l'id comme condition car nous recherchons par Id
						PreparedStatement preparedSatatement = connection.prepareStatement("SELECT * FROM t_clients WHERE IdClient = ? ");
						preparedSatatement.setInt(1, IdClient);
						
						//on execute la requete
						ResultSet result = preparedSatatement.executeQuery();
						
						//Pas de boucle si nous recherchon par Id
						if(result.next()) {
							return new Client(
									result.getInt("idClient"),
									result.getString("nom"),
									result.getString("prenom"),
									result.getString("Email"),
									result.getString("Adresse"),
									result.getString("telephone"),
									result.getInt("idUser"));
						}
						
					}catch(SQLException e) {
						e.printStackTrace();
					}
					return null;
			}
			//mise à jour de l'objet articles grace a son id
			@Override
			public boolean update(Client obj) {
				// on appelle la fonciton static Connect de la class BddConnection
				connection =  (Connection) BddConnection.Connect();
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("UPDATE t_clients SET Nom = ?, Prenom = ?, email = ?, Adresse = ?, Telephone = ? WHERE idClient = ? ");
					preparedStatement.setString(1, obj.getNom());
					preparedStatement.setString(1, obj.getPrenom());
					preparedStatement.setString(1, obj.getEmail());
					preparedStatement.setString(1, obj.getAdresse());
					preparedStatement.setString(1, obj.getTelephone());
					preparedStatement.setInt(3, obj.getIdClient());
					
					//on return true si l'update a été effectué
					return preparedStatement.executeUpdate() == 1;
				}catch(SQLException e) {
					e.printStackTrace();
				}
				//sinon on return false
				return false;
			}
			@Override
			public boolean delete(int IdClient) {
				// on appelle la fonciton static Connect de la class BddConnection
				connection =  (Connection) BddConnection.Connect();
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_clients WHERE idClient = ?");
					preparedStatement.setInt(1, IdClient);
					// return true si return 1
					return preparedStatement.executeUpdate() == 1;
					 
				}catch(SQLException e) {
					e.printStackTrace();
				}
				return false; 
			}
			// on obtient la liste des articles
			@Override
			public ArrayList<Client> readAll(){
				// on appelle la fonciton static Connect de la class BddConnection
				ArrayList<Client> listClient = new ArrayList<Client>(); 
				
				connection =  (Connection) BddConnection.Connect();
				try {
					//on prepare la requete
					PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_clients");
					//on execute la requete avec ResultSet
					ResultSet result = preparedStatement.executeQuery();
					//on parcour les resultats avec une boucle while
					while(result.next()) {
						//on recupere get les donnes des propriété
						Client client = new Client(
								result.getInt("idClient"),
								result.getString("nom"),
								result.getString("prenom"),
								result.getString("Email"),
								result.getString("Adresse"),
								result.getString("Telephone"),
								result.getInt("idUser"));
						//on ajoute les données a la liste
						listClient.add(client);
								
					}
					//on return la liste des articles
					return listClient; 
					
					
				}catch(SQLException e ) {
					e.printStackTrace();
				}
				return null; 
			 }
}
