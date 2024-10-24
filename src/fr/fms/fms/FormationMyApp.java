package fr.fms.fms;
import fr.fms.business.IBusinessImpl;
import fr.fms.entities.*;
import fr.fms.dao.*;
import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.CategoryDao;
import fr.fms.dao.FormationDao;
import fr.fms.entities.Formation;

public class FormationMyApp {
	private static Scanner scan = new Scanner(System.in); 
	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_NOMFORMATION = "NOM FORMATION";
	private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	private static final String COLUMN_DUREEJOUR = "DUREE JOUR";
	private static final String COLUMN_DIST_PRESENS = "DISTANCIEL/PRESENTIEL";
	private static final String COLUMN_PRIX = "PRIX";
	private static final String COLUMN_NOMCATEGORIE  = "NOM CATEGORIES";
	private static final String COLUMN_IDCATEGORIE  = "IDCATEGORIES";
	private static final String COLUMN_DESCRIPTIONCATEGORIE  = "DESCRIPTION";
	
	
	public static IBusinessImpl business = new IBusinessImpl();
	static CategoryDao cDao = new CategoryDao();
	static FormationDao  fDao = new FormationDao();
	static ArrayList<Formation> listFormationPanier = new ArrayList<Formation>();
	
	public static void main(String[] args) {
		//fdao.readAll();
		//System.out.println(fdao.readAll());
		//fdao.readAllByIdCategory(2);
		//System.out.println(cDao.readAll());
		//System.out.println(fdao.readAllByIdCategory(3));
		//displayFormation();
		//String str = "java avancé";
		//System.out.println(fdao.readAllByWordKey(str));
		//System.out.println(fdao.readAllByPresentielDistanciel("presentiel"));
		
		System.out.println("Bienvenu au centre commercial des formations");
		displayMenu();
		int choix = 0;
		while(choix != 10) {
			choix = scan.nextInt();
			
			switch(choix) {
				case 1:
					addFormation();
					break;
				case 2:
					removeFormation();
					break;
				case 3:
					displayCart(true);
					break;
				case 4:
					displayFormation();
					break; 
				case 5:
					displayCategorie();
					break;
				case 6:
					displayFormationByCategorie();
					break; 
				case 7:
					displayFormationByWordKey();
					break;
				case 8:
					displayFormationPresentielDistanciel();
					break;
				case 9:
					displayMenu();
					break;
				
			}
		}
		
		
	}
	public static void displayMenu() {	
		//if(login != null)	System.out.print(TEXT_BLUE + "Compte : " + login);
		System.out.println("\n" + "Pour réaliser une action, tapez le code correspondant");
		System.out.println("1 : Ajouter une formation au panier");
		System.out.println("2 : Retirer une formation du panier");
		System.out.println("3 : Afficher mon panier + total pour passer commande");
		System.out.println("4 : Afficher toute les formations");
		System.out.println("5 : Afficher toutes les catégories ");
		System.out.println("6 : Afficher toutes les formations d'une catégorie");
		System.out.println("7 : Afficher (les) formation par mot clé ");
		System.out.println("8 : Afficher les formations en presentiel ou en distanciel ");
		System.out.println("9 : Connexion(Deconnexion) à votre compte");
		System.out.println("9 : Retourner au Menu");
		System.out.println("10 : Sortir de l'application");
	}
	public static void displayFormation() { 	
		System.out.println("LISTES DES FORMATIONS :");
		System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("%-15s | %-15s | %-35s | %-15s | %-25s | %-15s %n",COLUMN_ID,COLUMN_NOMFORMATION,COLUMN_DESCRIPTION,COLUMN_DUREEJOUR, COLUMN_DIST_PRESENS, COLUMN_PRIX );
		System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
		fDao.readAll().forEach( a -> System.out.printf("%-15s | %-15s | %-35s | %-15s | %-25s | %-15s%n",a.getIdFormation(),a.getNomFormation(),a.getDescriptionFormation(), a.getDureeJour(), a.getPresentielDistanciel(), a.getPrix()));
	}
	public static void displayCategorie() { 	
		System.out.println("LISTES DES CATEGORIES :");
		System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("%-15s | %-20s | %-20s %n",COLUMN_ID,COLUMN_NOMCATEGORIE,COLUMN_DESCRIPTIONCATEGORIE );
		System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
		cDao.readAll().forEach( a -> System.out.printf("%-15s | %-20s | %-20s%n",a.getIdCatgory(),a.getNomCategory(),a.getDescriptionCategory()));
	}
	public static void displayFormationByCategorie() {
		System.out.println("Entrez numéro de la categorie");
		int idCat = scan.nextInt();
		System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("%-15s | %-15s | %-35s | %-15s | %-25s| %-25s | %-15s %n",COLUMN_ID,COLUMN_NOMFORMATION,COLUMN_DESCRIPTION,COLUMN_DUREEJOUR, COLUMN_DIST_PRESENS, COLUMN_PRIX, COLUMN_IDCATEGORIE );
		System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
		fDao.readAllByIdCategory(idCat).forEach(a -> System.out.printf("%-15s | %-15s | %-35s | %-15s | %-25s |%-25s | %-15s%n",a.getIdFormation(),a.getNomFormation(),a.getDescriptionFormation(), a.getDureeJour(), a.getPresentielDistanciel(), a.getPrix(), a.getIdCategory()));
		scan.nextLine();
	}
	public static void displayFormationPresentielDistanciel() {
		System.out.println("Choisir presentiel ou distanciel pour la formation (presentiel/distanciel)");
		String str = scan.nextLine();
		System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("%-15s | %-15s | %-35s | %-15s | %-25s | %-15s %n",COLUMN_ID,COLUMN_NOMFORMATION,COLUMN_DESCRIPTION,COLUMN_DUREEJOUR, COLUMN_DIST_PRESENS, COLUMN_PRIX );
		System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
		fDao.readAllByPresentielDistanciel(str).forEach(a -> System.out.printf("%-15s | %-15s | %-35s | %-15s | %-25s | %-15s%n",a.getIdFormation(),a.getNomFormation(),a.getDescriptionFormation(),a.getDureeJour(), a.getPresentielDistanciel(), a.getPrix()));
		scan.nextLine();
		
		
	}
	public static void displayFormationByWordKey() {
		System.out.println("Entrez mot clé");
		String str = scan.nextLine();
		try {
			System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("%-15s | %-15s | %-35s | %-15s | %-25s | %-15s %n",COLUMN_ID,COLUMN_NOMFORMATION,COLUMN_DESCRIPTION,COLUMN_DUREEJOUR, COLUMN_DIST_PRESENS, COLUMN_PRIX );
			System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------%n");
			fDao.readAllByWordKey(str).forEach(a -> System.out.printf("%-15s | %-15s | %-35s | %-15s | %-25s | %-15s%n",a.getIdFormation(),a.getNomFormation(),a.getDescriptionFormation(),a.getDureeJour(), a.getPresentielDistanciel(), a.getPrix()));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		scan.nextLine();
	}
	
	public static void removeFormation() {
		System.out.println("Selectionner l'id de l'article à supprimer du panier");
		int id = scanInt();
		listFormationPanier.remove(id - 1);
		displayCart(false);
	}
	public static void displayCart(boolean flag) {
		//if(business.isCartEmpty()) 	System.out.println("PANIER VIDE");
		//else{
			System.out.println("CONTENU DU PANIER :");
			System.out.printf("----------------------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("%-15s | %-15s | %-35s | %-15s | %-25s | %-15s %n",COLUMN_ID,COLUMN_NOMFORMATION,COLUMN_DESCRIPTION,COLUMN_DUREEJOUR, COLUMN_DIST_PRESENS, COLUMN_PRIX );
			System.out.printf("----------------------------------------------------------------------------------------------------------------------------------%n");
			
			
			listFormationPanier.forEach( a -> System.out.printf("%-15s | %-15s | %-35s | %-15s | %-25s | %-15s%n",a.getIdFormation(),a.getNomFormation(),a.getDescriptionFormation(), a.getDureeJour(), a.getPresentielDistanciel(), a.getPrix()));
			double TotaleFormation = 0;
			for(Formation sum : listFormationPanier) {
				TotaleFormation += sum.getPrix();
			}
			if(flag) {
				System.out.println("MONTANT TOTAL : " + TotaleFormation+ "€");
				System.out.println("Souhaitez vous passer commande ? Oui/Non :");
				if(scan.next().equalsIgnoreCase("Oui")) {
//					nextStep();
				}
			}
		//}
	}
	public static void addFormation() {
		System.out.println("Selectionner l'id de la Formation à ajouter au panier");
		int id = scanInt();
		Formation formation = fDao.read(id);
		if(formation != null) {
			listFormationPanier.add(formation);
			displayCart(false);
		}
		else System.out.println("la formation que vous souhaitez ajouter n'existe pas");
		//displayCart();
	} 
	public static int scanInt() {
		while(!scan.hasNextInt()) {
			System.out.println("saisissez une valeur entière svp");
			scan.next();
		}
		return scan.nextInt();
	}
}
