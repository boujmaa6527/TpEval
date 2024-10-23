package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Categorie;
import fr.fms.entities.Formation;

public class CategoryDao implements Dao<Categorie>{
	
	
		private ArrayList<Categorie>  listCategorie; 
		private Connection connection; 
		private BddConnection bddConnection; 
		
		//contructeur pour la connexion a notre BDD le chemin, login et pwd
		public CategoryDao(){
			listCategorie = new ArrayList<Categorie>();
			
		}
		
			//enregiste une occurence dans la bdd
			@Override
			public boolean create(Categorie obj) {
				// on appelle la fonciton static Connect de la class BddConnection
				//connection =  (Connection) BddConnection.Connect();
				
				try(Statement statement = connection.createStatement()){
					String str = "INSERT INTO t_categories (NomCategory, Description)"
							+ "VALUES ('" + obj.getNomCategory() +"' ,'"+
											obj.getDescriptionCategory()+");";
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
			public Categorie read(int IdCategory) {
				// on appelle la fonciton static Connect de la class BddConnection
				connection =  (Connection) BddConnection.Connect();
					try {
						//on prepare la requete avec l'id comme condition car nous recherchons par Id
						PreparedStatement preparedSatatement = connection.prepareStatement("SELECT * FROM t_categories WHERE IdCategory = ? ");
						preparedSatatement.setInt(1, IdCategory);
						
						//on execute la requete
						ResultSet result = preparedSatatement.executeQuery();
						
						//Pas de boucle si nous recherchon par Id
						if(result.next()) {
							return new Categorie(
									result.getInt("idCategory"),
									result.getString("nomCategorie"),
									result.getString("Description"));
									
						}
						
					}catch(SQLException e) {
						e.printStackTrace();
					}
					return null;
			}
			//mise à jour de l'objet articles grace a son id
			@Override
			public boolean update(Categorie obj) {
				// on appelle la fonciton static Connect de la class BddConnection
				connection =  (Connection) BddConnection.Connect();
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("UPDATE t_Categories SET NomCategorie = ?, Description = ?, WHERE idCategory = ? ");
					preparedStatement.setString(1, obj.getNomCategory());
					preparedStatement.setString(1, obj.getDescriptionCategory());
					preparedStatement.setInt(3, obj.getIdCatgory());
					
					//on return true si l'update a été effectué
					return preparedStatement.executeUpdate() == 1;
				}catch(SQLException e) {
					e.printStackTrace();
				}
				//sinon on return false
				return false;
			}
			@Override
			public boolean delete(int IdCategory) {
				// on appelle la fonciton static Connect de la class BddConnection
				connection =  (Connection) BddConnection.Connect();
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_categories WHERE idCategory = ?");
					preparedStatement.setInt(1, IdCategory);
					// return true si return 1
					return preparedStatement.executeUpdate() == 1;
					 
				}catch(SQLException e) {
					e.printStackTrace();
				}
				return false; 
			}
			// on obtient la liste des articles
			@Override
			public ArrayList<Categorie> readAll(){
				// on appelle la fonciton static Connect de la class BddConnection
				ArrayList<Categorie> listCategorie = new ArrayList<Categorie>(); 
				
				connection =  (Connection) BddConnection.Connect();
				try {
					//on prepare la requete
					PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_categories");
					//on execute la requete avec ResultSet
					ResultSet result = preparedStatement.executeQuery();
					//on parcour les resultats avec une boucle while
					while(result.next()) {
						//on recupere get les donnes des propriété
						Categorie categorie = new Categorie(
								result.getInt("idCategory"),
								result.getString("NomCategorie"),
								result.getString("Description"));
						//on ajoute les données a la liste
						listCategorie.add(categorie);
								
					}
					//on return la liste des articles
					return listCategorie; 
					
					
				}catch(SQLException e ) {
					e.printStackTrace();
				}
				return null; 
		 }


	

}
