package fr.fms.fms;

import java.util.Scanner;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.CategoryDao;
import fr.fms.dao.FormationDao;

public class FormationMyApp {
	private static Scanner scan = new Scanner(System.in); 
	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_NOMFORMATION = "NOM FORMATION";
	private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	private static final String COLUMN_DUREEJOUR = "DUREE JOUR";
	private static final String COLUMN_DIST_PRESENS = "DISTANCIEL/PRESENTIEL";
	private static final String COLUMN_PRIX = "PRIX";
	
	
	private static IBusinessImpl business = new IBusinessImpl();
	FormationDao fDao = new FormationDao();
	CategoryDao cDao = new CategoryDao();

	public static void main(String[] args) {
		CategoryDao cDao = new CategoryDao();
		business.readAll();
		FormationDao  fdao = new FormationDao();
		//fdao.readAll();
		//System.out.println(fdao.readAll());
		//fdao.readAllByIdCategory(2);
		//System.out.println(cDao.readAll());
		//System.out.println(fdao.readAllByIdCategory(3));
		//displayFormation();
		//System.out.println(fdao.readAllByWordKey("java avancé"));
		System.out.println(fdao.readAllByPresentielDistanciel("presentiel"));
		
		
	}
	public static void displayMenu() {	
		//if(login != null)	System.out.print(TEXT_BLUE + "Compte : " + login);
		System.out.println("\n" + "Pour réaliser une action, tapez le code correspondant");
		System.out.println("1 : Ajouter une formation au panier");
		System.out.println("2 : Retirer une formation du panier");
		System.out.println("3 : Afficher mon panier + total pour passer commande");
		System.out.println("4 : Afficher toute les formations");
		System.out.println("5 : Afficher toutes les catégories en base");
		System.out.println("6 : Afficher tous les formations d'une catégorie");
		System.out.println("7 : Connexion(Deconnexion) à votre compte");
		System.out.println("8 : sortir de l'application");
	}
	public static void displayFormation() { 				
		System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("%s | %s | %s | %s | %s | %s %n",COLUMN_ID,COLUMN_NOMFORMATION,COLUMN_DESCRIPTION,COLUMN_DUREEJOUR, COLUMN_DIST_PRESENS, COLUMN_PRIX );
		System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
		business.readAll().forEach( a -> System.out.printf("%s | %s | %s | %s | %s | %s%n",a.getIdFormation(),a.getNomFormation(),a.getDescriptionFormation(), a.getDureeJour(), a.getPresentielDistanciel(), a.getPrix()));
	}
}
