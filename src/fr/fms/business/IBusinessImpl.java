package fr.fms.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import fr.fms.dao.BddConnection;
import fr.fms.dao.ClientDao;
import fr.fms.dao.CommandeDao;
import fr.fms.dao.DaoFactory;
import fr.fms.entities.Commande;
import fr.fms.entities.Formation;

public  class IBusinessImpl implements IBusiness<Formation>{
	private HashMap<Integer,Formation> cart;
	private Connection connection; 
	
	// Constructeur
	public IBusinessImpl() {
		this.cart = new HashMap<Integer, Formation>();
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
	//ajoute au panier
	@Override
	public void addToCart(Formation formation) {
		
		Formation forma = cart.get(formation.getIdFormation());
		if(forma != null) {
			cart.put(formation.getIdFormation(), formation);
		}
			
	}
	//supprime du panier
	@Override
	public void removeFromCart(int id) {
		Formation forma = cart.get(id);
		if(forma != null) {
			cart.remove(id);
		}
	}
	@Override
	public  ArrayList<Formation> getCart(){
		return new ArrayList<Formation> (cart.values());
		
	}
	
	@Override
	public boolean order(int idClient) {
		ClientDao clientDao = new ClientDao();
		CommandeDao cDao = new CommandeDao();
			double total = getTotal();
			if(clientDao.read(idClient) != null) {
				Commande commande = new Commande(total, new Date(),idClient);
				if(cDao.create(commande)) {
					return true; 
				}
			}
		return false;
	}
	
	public double getTotal() {
		double [] total = {0};
		cart.values().forEach((a) -> total[0] += a.getPrix()); 	
		return total[0];
	}
	public boolean isCartEmpty() {
		return cart.isEmpty();
	}
	
	public void clearCart() {
		cart.clear();		
	}
	
	
}
