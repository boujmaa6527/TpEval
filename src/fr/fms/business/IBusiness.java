package fr.fms.business;

import java.util.ArrayList;

public interface IBusiness <T> {
	

	//public void create(T obj);//ajout d'une nouvelle occurence en base
	//public T read(int id);// renvoie un objet correspondant à l'id en base
	//public boolean update(T obj);//met à jour l'objet en base, return true
	//public boolean delete(int Id);//supprime un objet en base et return true
	public ArrayList<T> readAll();//renvoie tous les objets de la table correspondante	

}
