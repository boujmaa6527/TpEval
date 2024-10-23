package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Formation;

public interface IBusiness <T> {
	

	//public void create(T obj);//ajout d'une nouvelle occurence en base
	//public T read(int id);// renvoie un objet correspondant à l'id en base
	//public boolean update(T obj);//met à jour l'objet en base, return true
	//public boolean delete(int Id);//supprime un objet en base et return true
	public ArrayList<T> readAll();//renvoie tous les objets de la table correspondante	
	
	public void addToCart(Formation formation);		
	
	/**
	 * méthode qui retire un article au panier s'il est dedans
	 * @param id de l'article à retirer
	 */
	public void removeFromCart(int id);		
	
	/**
	 * méthode qui renvoi sous la forme d'une liste tous les éléments du panier (gestion en mémoire)
	 * @return Liste d'articles du panier
	 */
	public ArrayList<Formation> getCart();	
	
	public boolean order(int idUser);	
}
