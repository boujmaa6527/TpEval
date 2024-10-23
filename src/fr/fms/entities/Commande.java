package fr.fms.entities;

import java.util.Date;

public class Commande {
	
	private int idCommande;
	private double amount;
	private Date dateCommande; 
	private int idClient;
	public Commande(int idCommande, double amount, Date dateCommande, int idClient) {
		super();
		this.idCommande = idCommande;
		this.amount = amount;
		this.dateCommande = dateCommande;
		this.idClient = idClient;
	}
	public Commande(double amount, Date dateCommande, int idClient) {
		super();
		this.amount = amount;
		this.dateCommande = dateCommande;
		this.idClient = idClient;
	}
	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", amount=" + amount + ", dateCommande=" + dateCommande
				+ ", idClient=" + idClient + "]";
	} 
	

}
