package fr.fms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Categorie;
import fr.fms.entities.Commande;

public  class CommandeDao implements Dao<Commande> {
	

		private ArrayList<Commande>  listCommande; 
		private Connection connection; 
		private BddConnection bddConnection; 
		
		
		public CommandeDao(){
			listCommande = new ArrayList<Commande>();
			
		}
		
			//enregiste une occurence dans la bdd
			@Override
			public boolean create(Commande obj) {
				// on appelle la fonciton static Connect de la class BddConnection
				//connection =  (Connection) BddConnection.Connect();
				
				try(Statement statement = connection.createStatement()){
					String str = "INSERT INTO t_commandes (Amount, DateCommande,IdUser)"
							+ "VALUES ('" + obj.getAmount() +"' ,'"+ 
											obj.getDateCommande()+"', "+ obj.getIdClient()+");";
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
			public Commande read(int IdCommande) {
				// on appelle la fonciton static Connect de la class BddConnection
				connection =  (Connection) BddConnection.Connect();
					try {
						//on prepare la requete avec l'id comme condition car nous recherchons par Id
						PreparedStatement preparedSatatement = connection.prepareStatement("SELECT * FROM t_commandes WHERE IdCommande = ? ");
						preparedSatatement.setInt(1, IdCommande);
						
						//on execute la requete
						ResultSet result = preparedSatatement.executeQuery();
						
						//Pas de boucle si nous recherchon par Id
						if(result.next()) {
							return new Commande(
									result.getInt("idCommande"),
									result.getDouble("Amount"),
									result.getDate("dateCommande"),
									result.getInt("idClient"));
									
						}
						
					}catch(SQLException e) {
						e.printStackTrace();
					}
					return null;
			}
			//mise à jour de l'objet articles grace a son id
			@Override
			public boolean update(Commande obj) {
				// on appelle la fonciton static Connect de la class BddConnection
				connection =  (Connection) BddConnection.Connect();
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("UPDATE t_commandes SET Amount = ?, DateCommande = ?, idUser =? WHERE idCommande = ? ");
					preparedStatement.setDouble(1, obj.getAmount());
					preparedStatement.setDate(1, (Date) obj.getDateCommande());
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
			public boolean delete(int IdCommande) {
				// on appelle la fonciton static Connect de la class BddConnection
				connection =  (Connection) BddConnection.Connect();
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_commandes WHERE idCommande = ?");
					preparedStatement.setInt(1, IdCommande);
					// return true si return 1
					return preparedStatement.executeUpdate() == 1;
					 
				}catch(SQLException e) {
					e.printStackTrace();
				}
				return false; 
			}
			// on obtient la liste des articles
			@Override
			public ArrayList<Commande> readAll(){
				// on appelle la fonciton static Connect de la class BddConnection
				ArrayList<Commande> listCommande = new ArrayList<Commande>(); 
				
				connection =  (Connection) BddConnection.Connect();
				try {
					//on prepare la requete
					PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_commandes");
					//on execute la requete avec ResultSet
					ResultSet result = preparedStatement.executeQuery();
					//on parcour les resultats avec une boucle while
					while(result.next()) {
						//on recupere get les donnes des propriété
						Commande commande = new Commande(
								result.getInt("idCommande"),
								result.getDouble("Amount"),
								result.getDate("DateCommande"),
								result.getInt("idClient"));
						//on ajoute les données a la liste
						listCommande.add(commande);
								
					}
					//on return la liste des commande
					return listCommande; 
					
					
				}catch(SQLException e ) {
					e.printStackTrace();
				}
				return null; 
		 }


}
