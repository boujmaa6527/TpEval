package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import fr.fms.entities.Formation;

	public  class FormationDao  implements Dao<Formation>{
		
		private ArrayList<Formation>  listFormation; 
		private Connection connection; 
		private BddConnection bddConnection; 
		
		//contructeur pour la connexion a notre BDD le chemin, login et pwd
		public FormationDao(){
			listFormation = new ArrayList<Formation>();
			
		}
		
	//enregiste une occurence dans la bdd
	@Override
	public boolean create(Formation obj) {
		// on appelle la fonciton static Connect de la class BddConnection
		//connection =  (Connection) BddConnection.Connect();
		
		try(Statement statement = connection.createStatement()){
			String str = "INSERT INTO t_formations (NomFormation, DescriptionFormation, DureeJour, DistancielPresentiel, Prix, IdCategory)"
					+ "VALUES ('" + obj.getIdFormation() +"' ,'"+
									obj.getDescriptionFormation() +"' , "+ 
									obj.getDureeJour()+" , "+ 
									obj.getPresentielDistanciel()+" , "+ obj.getPrix()+ ", "+obj.getIdCategory()+");";
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
	public Formation read(int IdFormation) {
		// on appelle la fonciton static Connect de la class BddConnection
		connection =  (Connection) BddConnection.Connect();
			try {
				//on prepare la requete avec l'id comme condition car nous recherchons par Id
				PreparedStatement preparedSatatement = connection.prepareStatement("SELECT * FROM t_formations WHERE IdFormation = ? ");
				preparedSatatement.setInt(1, IdFormation);
				
				//on execute la requete
				ResultSet result = preparedSatatement.executeQuery();
				
				//Pas de boucle si nous recherchon par Id
				if(result.next()) {
					return new Formation(
							result.getInt("idFormation"),
							result.getString("nomFormation"),
							result.getString("descriptionFormation"),
							result.getInt("dureeJour"),
							result.getString("distancielPresentiel"),
							result.getDouble("prix"),
							result.getInt("idCategory"));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
	}
	//mise à jour de l'objet articles grace a son id
	@Override
	public boolean update(Formation obj) {
		// on appelle la fonciton static Connect de la class BddConnection
		connection =  (Connection) BddConnection.Connect();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE t_Formations SET NomFormation = ?, DescriptionFormation = ?, DureeJour = ?, distancielPresentiel = ?, prix = ? WHERE idFormation = ? ");
			preparedStatement.setString(1, obj.getNomFormation());
			preparedStatement.setString(1, obj.getDescriptionFormation());
			preparedStatement.setInt(1, obj.getDureeJour());
			preparedStatement.setString(1, obj.getPresentielDistanciel());
			preparedStatement.setDouble(1, obj.getPrix());
			preparedStatement.setInt(3, obj.getIdFormation());
			
			//on return true si l'update a été effectué
			return preparedStatement.executeUpdate() == 1;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//sinon on return false
		return false;
	}
	@Override
	public boolean delete(int IdFormation) {
		// on appelle la fonciton static Connect de la class BddConnection
		connection =  (Connection) BddConnection.Connect();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_Formations WHERE idFormation = ?");
			preparedStatement.setInt(1, IdFormation);
			// return true si return 1
			return preparedStatement.executeUpdate() == 1;
			 
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false; 
	}
	// on obtient la liste des articles
	@Override
	public ArrayList<Formation> readAll(){
		// on appelle la fonciton static Connect de la class BddConnection
		ArrayList<Formation> listFormation = new ArrayList<Formation>(); 
		
		connection =  (Connection) BddConnection.Connect();
		try {
			//on prepare la requete
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_formations");
			//on execute la requete avec ResultSet
			ResultSet result = preparedStatement.executeQuery();
			//on parcour les resultats avec une boucle while
			while(result.next()) {
				//on recupere get les donnes des propriété
				Formation formation = new Formation(
						result.getInt("idFormation"),
						result.getString("nomFormation"),
						result.getString("descriptionFormation"),
						result.getInt("dureeJour"),
						result.getString("distancielPresentiel"),
						result.getDouble("prix"),
						result.getInt("idCategory"));
				//on ajoute les données a la liste
				listFormation.add(formation);
						
			}
			//on return la liste des articles
			return listFormation; 
			
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		return null; 
	 }
	//revoie les formation par IdCatgorie
	public ArrayList<Formation> readAllByIdCategory(int idCategory){
		// on appelle la fonciton static Connect de la class BddConnection
		ArrayList<Formation> listFormationByIdCatgory = new ArrayList<Formation>(); 
		connection =  (Connection) BddConnection.Connect();
		try {
			//on prepare la requete
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_formations WHERE idCategory = ?");
			//on execute la requete avec ResultSet
			preparedStatement.setInt(1, idCategory);
			ResultSet result = preparedStatement.executeQuery();
			//on parcour les resultats avec une boucle while
			while(result.next()) {
				//on recupere get les donnes des propriété
				Formation formation = new Formation(
						result.getInt("idFormation"),
						result.getString("nomFormation"),
						result.getString("descriptionFormation"),
						result.getInt("dureeJour"),
						result.getString("distancielPresentiel"),
						result.getDouble("prix"),
						result.getInt("idCategory"));
				//on ajoute les données a la liste
				listFormationByIdCatgory.add(formation);
						
			}
			//on return la liste des articles
			return listFormationByIdCatgory; 
			
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		return null; 
	 }
	// renvoie les formations par mot clé
	public ArrayList<Formation> readAllByWordKey(String str){
		connection =  (Connection) BddConnection.Connect();
		FormationDao fdao = new FormationDao();
		ArrayList<Formation> listFormationByWordKey = new ArrayList<Formation>();

		for(Formation formation: fdao.readAll()) {
			if(formation.getNomFormation().equalsIgnoreCase(str)) {
				listFormationByWordKey.add(formation);
				//System.out.println(listFormationByWordKey);
				}
			//System.out.println(listFormationByWordKey);
			}
			return listFormationByWordKey; 
		}
	

	public ArrayList<Formation> readAllByPresentielDistanciel(String str){
		connection =  (Connection) BddConnection.Connect();
		FormationDao fdao = new FormationDao();
		ArrayList<Formation> listFormationByWordKey = new ArrayList<Formation>();

		for(Formation formation: fdao.readAll()) {
			if(formation.getPresentielDistanciel().equalsIgnoreCase(str)) {
				listFormationByWordKey.add(formation);
				//System.out.println(listFormationByWordKey);
				}
			//System.out.println(listFormationByWordKey);
			}
			return listFormationByWordKey; 
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
