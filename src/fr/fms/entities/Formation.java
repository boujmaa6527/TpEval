package fr.fms.entities;

public class Formation {
	
	private int IdFormation;
	private String nomFormation;
	private String descriptionFormation; 
	private int dureeJour;
	private boolean presentielDistanciel;
	private double prix;
	private int idCategory; 
	
	public Formation(int idFormation, String nomFormation, String descriptionFormation, int dureeJour,
			boolean	presentielDistanciel,double  prix, int idCategory) {
		this.IdFormation = idFormation; 
		this.nomFormation = nomFormation;
		this.descriptionFormation = descriptionFormation;
		this.dureeJour = dureeJour;
		this.presentielDistanciel =presentielDistanciel; 
		this.prix = prix;
		this.idCategory = idCategory; 
	} 
	public Formation( String nomFormation, String descriptionFormation, int dureeJour,
			boolean presentielDistanciel, double prix, int idCategory) {
		
		this.nomFormation = nomFormation;
		this.descriptionFormation = descriptionFormation;
		this.dureeJour = dureeJour;
		this.presentielDistanciel = presentielDistanciel;
		this.prix = prix;
		this.idCategory = idCategory;
	} 
	
	
	
	public int getIdFormation() {
		return IdFormation;
	}

	public void setIdFormation(int idFormation) {
		IdFormation = idFormation;
	}

	public String getNomFormation() {
		return nomFormation;
	}

	public void setNomFormation(String nomFormation) {
		this.nomFormation = nomFormation;
	}

	public String getDescriptionFormation() {
		return descriptionFormation;
	}

	public void setDescriptionFormation(String descriptionFormation) {
		this.descriptionFormation = descriptionFormation;
	}

	public int getDureeJour() {
		return dureeJour;
	}

	public void setDureeJour(int dureeJour) {
		this.dureeJour = dureeJour;
	}

	public boolean getPresentielDistanciel() {
		return presentielDistanciel;
	}

	public void setPresentielDistanciel(boolean presentielDistanciel) {
		this.presentielDistanciel = presentielDistanciel;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	@Override
	public String toString() {
		return "Formation [IdFormation=" + IdFormation + ", nomFormation=" + nomFormation + ", descriptionFormation="
				+ descriptionFormation + ", dureeJour=" + dureeJour + ", presentielDistanciel=" + presentielDistanciel
				+ ", prix=" + prix + ", idCategory=" + idCategory + "]";
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	

}
