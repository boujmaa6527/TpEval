package fr.fms.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.fms.dao.BddConnection;
import fr.fms.entities.Formation;

public  class IBusinessImpl implements IBusiness<Formation>{
	
	private Connection connection; 
	private BddConnection bddConnection; 
	private HashMap<Integer, Formation> formations;
	// Constructeur
	
	public IBusinessImpl() {
		this.formations = new HashMap<>();
	}
	
	
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

}
